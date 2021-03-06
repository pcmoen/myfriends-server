package no.bouvet.sandvika.myfriends.rest.validator;

import no.bouvet.sandvika.myfriends.rest.model.User;
import no.bouvet.sandvika.myfriends.rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by sondre.engell on 19.01.2016.
 */

//@Component("beforeCreateUserValidator")
public class UserValidator implements Validator {

    @Autowired
    UserRepository userRepository;

    public UserValidator() {
        System.out.println("INIT!!");
    }


    @Override
    public boolean supports(Class<?> aClass) {
        System.out.println("Validating User!!");
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        System.out.println("Validating User!!");
        ValidationUtils.rejectIfEmpty(errors, "name", "name.empty");
        User user = (User) o;
        if (userRepository.findByUserName(user.getLastName()) != null) {
            errors.reject("Username already in use!");
        }


    }
}
