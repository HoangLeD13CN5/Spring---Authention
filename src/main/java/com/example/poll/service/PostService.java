package com.example.poll.service;

import com.example.poll.entity.Post;
import com.example.poll.exception.BadRequestException;
import com.example.poll.payload.request.PostRequest;
import com.example.poll.payload.respone.PagedResponse;
import com.example.poll.repository.PostRepository;
import com.example.poll.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    public PagedResponse getAllPost(int page, int size){
        validatePageNumberAndSize(page,size);
        Pageable pageable = PageRequest.of(page,size);
        Page<Post> posts = postRepository.findAll(pageable);
        return new PagedResponse<>(posts.getContent(), posts.getNumber(),
                posts.getSize(), posts.getTotalElements(), posts.getTotalPages(), posts.isLast());
    }

    public Post savePost(PostRequest postRequest){
        Post post = new Post();
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        return postRepository.save(post);

    }

    public PagedResponse getPostByUser(UserPrincipal userPrincipal,int page,int size){
        validatePageNumberAndSize(page,size);
        Pageable pageable = PageRequest.of(page,size, Sort.Direction.ASC,"createdAt");
        Page<Post> posts = postRepository.findByCreatedBy(userPrincipal.getId(),pageable);
        return new PagedResponse<>(posts.getContent(), posts.getNumber(),
                posts.getSize(), posts.getTotalElements(), posts.getTotalPages(), posts.isLast());
    }

    private void validatePageNumberAndSize(int page, int size){
        if(page < 0){
            throw new BadRequestException("Page number cannot be less zero");
        }
        if(size > AppConstants.MAX_PAGE_SIZE){
            throw new BadRequestException("Size cannot be less than "+AppConstants.MAX_PAGE_SIZE);
        }
    }
}
