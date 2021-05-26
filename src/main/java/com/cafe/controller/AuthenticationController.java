package com.cafe.controller;

import com.cafe.config.security.TokenProvider;
import com.cafe.model.dto.user.AuthTokenDTO;
import com.cafe.model.dto.SignInRequest;
import com.cafe.utils.DecodeTokenUtil;
import com.cafe.model.ResponseModel;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.cafe.utils.constants.AppConstants.USERNAME_OR_PASSWORD_IS_INCORRECT;

@RequestMapping("auth/")
@RestController
public class AuthenticationController extends BaseController {

    @Lazy
    private final AuthenticationManager authenticationManager;
    @Lazy
    private final TokenProvider tokenProvider;

    public AuthenticationController(
            AuthenticationManager authenticationManager,
            TokenProvider tokenProvider
    ) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("signIn")
    public ResponseModel signIn(
            @Valid @RequestBody SignInRequest signInRequest,
            @RequestHeader("LanguageName") String languageName) {
        try {
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            signInRequest.getUsername(), signInRequest.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            final String token = tokenProvider.generateToken(authentication);
            final String roleName = DecodeTokenUtil.getRoleFromToken(token);

            AuthTokenDTO authTokenDTO = AuthTokenDTO.builder()
                    .token(token)
                    .role(roleName)
                    .build();
            return ResponseModel.builder()
                    .message("Ok")
                    .data(authTokenDTO)
                    .success(true)
                    .build();
        } catch (AuthenticationException e) {
            return createErrorResult(USERNAME_OR_PASSWORD_IS_INCORRECT(languageName));
        }
    }

}