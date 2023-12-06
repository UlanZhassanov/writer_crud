package com.ulanzhasssanov.writer_crud.repository.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.ulanzhasssanov.writer_crud.model.Post;
import com.ulanzhasssanov.writer_crud.enums.PostStatus;
import com.ulanzhasssanov.writer_crud.repository.PostRepository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GsonPostRepositoryImpl implements PostRepository {

    private final String jsonFilePath;

    public GsonPostRepositoryImpl(String jsonFilePath) {
        this.jsonFilePath = jsonFilePath;
    }

    @Override
    public Post getById(Integer id) {
        Post targetPost = null;
        List<Post> allPosts = getAll();
        if (allPosts != null) {
            for (Post post : allPosts) {
                if (post.getId() == id) {
                    targetPost = post;
                }
            }
        }
        return targetPost;
    }

    @Override
    public List<Post> getAll() {
        try (FileReader reader = new FileReader(jsonFilePath)){
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Post>>(){}.getType();
            return gson.fromJson(reader, listType);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Post save(Post post) {
        List<Post> postList = getAll();

        if (postList == null) {
            postList = new ArrayList<>();
            post.setId(1);
        } else {
            int lastPostlId = postList.get(postList.size() - 1).getId();
            post.setId(lastPostlId + 1);
        }

        postList.add(post);
        saveAll(postList);

        return post;
    }

    @Override
    public Post update(Post post) {
        List<Post> allPosts = getAll();

        if (allPosts != null) {
            for (int i = 0; i < allPosts.size(); i++) {
                if (allPosts.get(i).getId() == post.getId()) {
                    allPosts.set(i, post);
                    saveAll(allPosts);
                    return allPosts.get(i);
                }
            }
        }

        return null;
    }

    @Override
    public void deleteById(Integer id) {
        Post post = getById(id);
        post.setStatus(PostStatus.DELETED);
        post.setUpdated(LocalDateTime.now());

        update(post);
    }

    private void saveAll(List<Post> posts) {
        try (FileWriter writer = new FileWriter(jsonFilePath)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(posts, writer);
        } catch (IOException e) {
            e.printStackTrace();  // Handle the exception according to your needs
        }
    }
}
