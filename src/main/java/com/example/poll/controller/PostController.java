package com.example.poll.controller;

import com.example.poll.payload.request.PostRequest;
import com.example.poll.security.CurrentUser;
import com.example.poll.service.PostService;
import com.example.poll.service.UserPrincipal;
import com.example.poll.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    PostService postService;

    @GetMapping
    public ResponseEntity<?> getAllPost(@RequestParam(value = "page",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                        @RequestParam(value = "size",defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size){
            return ResponseEntity.ok(postService.getAllPost(page,size));
    }

    @PostMapping
    public ResponseEntity<?> createPost(@Valid @RequestBody PostRequest postRequest){
        return ResponseEntity.ok(postService.savePost(postRequest));
    }

    @GetMapping("/ofuser")
    public ResponseEntity<?> getPostByCreatedBy(@CurrentUser UserPrincipal userPrincipal,
                                                @RequestParam(value = "page",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                @RequestParam(value = "size",defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size){
        return ResponseEntity.ok(postService.getPostByUser(userPrincipal,page,size));
    }
}
