package com.skillbox.javapro21.service.serviceImpl;

import com.skillbox.javapro21.api.request.post.PostRequest;
import com.skillbox.javapro21.api.response.DataResponse;
import com.skillbox.javapro21.api.response.ListDataResponse;
import com.skillbox.javapro21.api.response.post.PostData;
import com.skillbox.javapro21.domain.Person;
import com.skillbox.javapro21.domain.Post;
import com.skillbox.javapro21.domain.PostLike;
import com.skillbox.javapro21.domain.Tag;
import com.skillbox.javapro21.exception.AuthorAndUserEqualsException;
import com.skillbox.javapro21.exception.PostNotFoundException;
import com.skillbox.javapro21.repository.PersonRepository;
import com.skillbox.javapro21.repository.PostLikeRepository;
import com.skillbox.javapro21.repository.PostRepository;
import com.skillbox.javapro21.repository.TagRepository;
import com.skillbox.javapro21.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class PostServiceImpl extends AbstractMethodClass implements PostService {
    private final PersonRepository personRepository;
    private final PostRepository postRepository;
    private final TagRepository tagRepository;
    private final PostLikeRepository postLikeRepository;

    @Autowired
    protected PostServiceImpl(PersonRepository personRepository, PostRepository postRepository, TagRepository tagRepository, PostLikeRepository postLikeRepository) {
        super(personRepository);
        this.personRepository = personRepository;
        this.postRepository = postRepository;
        this.tagRepository = tagRepository;
        this.postLikeRepository = postLikeRepository;
    }

    public ListDataResponse<PostData> getPosts(String text, long dateFrom, long dateTo, int offset, int itemPerPage, String author, String tag, Principal principal) {
        LocalDateTime datetimeFrom = (dateFrom != -1) ? getLocalDateTime(dateFrom) : LocalDateTime.now().minusYears(1);
        LocalDateTime datetimeTo = (dateTo != -1) ? getLocalDateTime(dateTo) : LocalDateTime.now();
        Pageable pageable = PageRequest.of(offset / itemPerPage, itemPerPage);
        Page<Post> pageablePostList;
        if (tag.equals("")) {
            pageablePostList = postRepository.findPostsByTextByAuthorWithoutTagsContainingByDateExcludingBlockers(text, datetimeFrom, datetimeTo, author, pageable);
        } else {
            List<Long> tags = getTags(tag);
            pageablePostList = postRepository.findPostsByTextByAuthorByTagsContainingByDateExcludingBlockers(text, datetimeFrom, datetimeTo, author, tags, pageable);
        }
        return getPostsResponse(offset, itemPerPage, pageablePostList);
    }

    public DataResponse<PostData> getPostsById(Long id, Principal principal) throws PostNotFoundException {
        PostData postData = getPostData(postRepository.findPostById(id).orElseThrow(() -> new PostNotFoundException("Поста с таким айди не существует")));
        return getDataResponse(postData);
    }

    public DataResponse<PostData> putPostByIdAndMessageInDay(Long id, long publishDate, PostRequest postRequest, Principal principal) throws PostNotFoundException, AuthorAndUserEqualsException {
        Optional<Person> person = personRepository.findByEmail(principal.getName());
        Post post = postRepository.findPostById(id).orElseThrow(() -> new PostNotFoundException("Поста с таким айди не существует"));
        if (!person.get().getId().equals(post.getAuthor().getId()))
            throw new AuthorAndUserEqualsException("Пользователь не может менять данные в этом посте");
        post.setTitle(postRequest.getTitle())
                .setPostText(postRequest.getPostText())
                .setTime((publishDate == -1) ? LocalDateTime.now() : getLocalDateTime(publishDate));
        post = postRepository.saveAndFlush(post);
        return getDataResponse(getPostData(post));
    }

    private DataResponse<PostData> getDataResponse(PostData postData) {
        return new DataResponse<PostData>()
                .setError("")
                .setTimestamp(LocalDateTime.now())
                .setData(postData);
    }

    private ListDataResponse<PostData> getPostsResponse(int offset, int itemPerPage, Page<Post> pageablePostList) {
        ListDataResponse<PostData> contentListDataResponse = new ListDataResponse<>();
        contentListDataResponse.setPerPage(itemPerPage);
        contentListDataResponse.setTimestamp(LocalDateTime.now());
        contentListDataResponse.setOffset(offset);
        contentListDataResponse.setTotal((int) pageablePostList.getTotalElements());
        contentListDataResponse.setData(getPostForResponse(pageablePostList.toList()));
        return contentListDataResponse;
    }

    private List<PostData> getPostForResponse(List<Post> listPosts) {
        List<PostData> postsDataList = new ArrayList<>();
        listPosts.forEach(post -> {
            PostData postData = getPostData(post);
            postsDataList.add(postData);
        });
        return postsDataList;
    }

    //todo: дописать добавление комментариев, как будут готовы
    private PostData getPostData(Post posts) {
        Set<PostLike> likes = postLikeRepository.findPostLikeByPostId(posts.getId());
        List<String> collect = null;
        if (posts.getTags() != null) collect = posts.getTags().stream().map(Tag::getTag).toList();
        return new PostData()
                .setId(posts.getId())
                .setTime(posts.getTime())
                .setAuthor(getAuthData(posts.getAuthor(), null))
                .setTitle(posts.getTitle())
                .setPostText(posts.getPostText())
                .setBlocked(posts.getIsBlocked() != 0)
                .setLikes(likes.size())
                .setComments(null)
                .setTags(collect);
    }

    private List<Long> getTags(String tag) {
        return Arrays.stream(tag.split(";"))
                .map(t -> tagRepository.findByTag(t).orElse(null))
                .filter(Objects::nonNull)
                .map(Tag::getId)
                .collect(Collectors.toList());
    }
}