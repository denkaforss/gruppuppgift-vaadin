package com.example.gruppuppgiftvaadin.frontend.views.components;

import com.example.gruppuppgiftvaadin.backend.entities.AppUser;
import com.example.gruppuppgiftvaadin.backend.services.AppUserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.binder.ValueContext;

public class RegisterForm extends FormLayout {




    AppUserService appUserService;
    private final BeanValidationBinder<AppUser> binder;

    private boolean enablePasswordValidation;


    public RegisterForm(AppUserService appUserService) {
        this.appUserService = appUserService;

        TextField userName = new TextField("User name");
        PasswordField passwordField1 = new PasswordField("Wanted password");
        PasswordField passwordField2 = new PasswordField("Password again");

        Span errorMessage = new Span();
        Button submitButton = new Button("Join Us or else!!");



        binder = new BeanValidationBinder<>(AppUser.class);

        binder.forField(userName).asRequired().bind("username");
        binder.forField(passwordField1).asRequired().withValidator(this::passwordValidator).bind("password");

        passwordField2.addValueChangeListener(e -> {

            enablePasswordValidation = true;

            binder.validate();
        });
        binder.setStatusLabel(errorMessage);

        submitButton.addClickListener(e -> {
            try {

                AppUser detailsBean = new AppUser();
                binder.writeBean(detailsBean);
                appUserService.storeUser(detailsBean);
                showSuccess(detailsBean);

            } catch (ValidationException e1) {
                e1.printStackTrace();
                errorMessage.setText("Saving data failed, try again");
            }
        });
    }

    private void showSuccess(AppUser detailBean){
        Notification notification = Notification.show("Data saved, Welcome i guess");
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
    }

    private ValidationResult passwordValidator(String pass1, ValueContext ctx) {
        if (pass1 == null || pass1.length() < 5) {
            return ValidationResult.error("password to short");
        }
        if (!enablePasswordValidation) {
            enablePasswordValidation = true;
            return ValidationResult.ok();
        }
        return ValidationResult.error("Passwords do not match");
    }
}
