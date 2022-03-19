package com.uniovi.sdipractica134.validators;

import com.uniovi.sdipractica134.entities.Post;
import com.uniovi.sdipractica134.services.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
@Component
public class PostFormValidator implements Validator {
    @Autowired
    private PostsService postsService;
    @Override
    public boolean supports(Class<?> clazz){return Post.class.equals(clazz);}

    @Override
    public void validate(Object target, Errors errors) {
        Post post=(Post) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"title","Error.posts.add.emptyField");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"description","Error.posts.add.emptyField");


        if(((Post) target).getTitle().length()<10){
            errors.rejectValue("title", "Error.posts.add.title.tooShort");
        }
        if(((Post) target).getDescription().length()<15){
            errors.rejectValue("description", "Error.posts.add.description.tooShort");
        }


    }

}
