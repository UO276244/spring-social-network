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

    /**
     * Valida si el usuario está creando una publicación válida
     * @param target publicación que intenta añadir
     * @param errors errores que comete
     */
    @Override
    public void validate(Object target, Errors errors) {
        Post post=(Post) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"title","Error.posts.add.empty.title");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"description","Error.posts.add.empty.description");

        if(post.getTitle().length()<10){
            errors.rejectValue("title", "Error.posts.add.title.tooShort");
        }

        if(post.getDescription().length()<15){
            errors.rejectValue("description", "Error.posts.add.description.tooShort");
        }

    }

}
