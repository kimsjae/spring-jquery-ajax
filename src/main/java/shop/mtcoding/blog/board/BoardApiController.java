package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardApiController {
    private final BoardRepository boardRepository;

    @GetMapping("/api/boards")
    public ApiUtil<?> findAll(HttpServletResponse response) {
        //response.setStatus(401);
        List<Board> boardList = boardRepository.selectAll();
        return new ApiUtil<>(boardList); // MessageConverter
    }
    // RestController면서 Object일 때 MessageConverter가 발동 = JSON으로 바뀜
}
