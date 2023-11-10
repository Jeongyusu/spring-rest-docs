package com.example.restdoc1_app.user;


import com.example.restdoc1_app.util.ApiUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class UserControllter {

    private final UserRepository userRepository;

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody @Valid UserRequest.JoinDTO requestDTO, Errors errors){ //User로 받으면 편하지만 밸리데이션 체크가 안됨
//        UserResponse.JoinDTO responseDTO = userService.회원가입();
        if(errors.hasErrors()){
            FieldError fieldError = errors.getFieldErrors().get(0);
            String key = fieldError.getField();
            String value = fieldError.getDefaultMessage();
            return new ResponseEntity<>(ApiUtil.error(value+" : "+ key), HttpStatus.BAD_REQUEST);
        }

        User user = userRepository.save(requestDTO.toEntity());

        return ResponseEntity.ok().body(ApiUtil.success(user)); // 이 방법 가장 추천
//        return ResponseEntity.ok(null);
//        return new ResponseEntity<>(null, HttpStatus.OK);
    }

//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody User user){
//        return ResponseEntity.ok().body(null); // 이 방법 가장 추천
////        return ResponseEntity.ok(null);
////        return new ResponseEntity<>(null, HttpStatus.OK);
//    }

    @GetMapping("/users/{id}") //주소에 적을 때는 _대신 -
    public ResponseEntity<?> userInfo(@PathVariable Integer id) {

        Optional<User> userOP = userRepository.findById(id);

        if (userOP.isEmpty()) {
            return new ResponseEntity<>(
                    ApiUtil.error("해당 아이디가 존재하지 않습니다"),
                    HttpStatus.NOT_FOUND
            );
        }
            return ResponseEntity.ok().body(ApiUtil.success(userOP.get()));
        }
}
