package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardApiController {
    private final BoardRepository boardRepository;

    @PutMapping("/api/boards/{id}")
    public ApiUtil<?> update(@RequestBody BoardRequest.WriteDTO requestDTO, @PathVariable Integer id) {
        boardRepository.update(requestDTO, id);
        return new ApiUtil<>(requestDTO);
    }


    @PostMapping("/api/boards")
    public ApiUtil<?> write(@RequestBody BoardRequest.WriteDTO requestDTO) { // JSON을 받을 수 있음
        boardRepository.insert(requestDTO);
        return new ApiUtil<>(null);
    }


    @DeleteMapping("/api/boards/{id}")
    public ApiUtil<?> deleteById(@PathVariable Integer id, HttpServletResponse response) {
        Board board = boardRepository.selectOne(id);
        if (board == null) {
            response.setStatus(404);
            return new ApiUtil<>(404, "해당 게시글을 찾을 수 없습니다.");
        }

        boardRepository.deleteById(id);
        return new ApiUtil<>(null);
    }


    @GetMapping("/api/boards")
    public ApiUtil<?> findAll(HttpServletResponse response) {
        //response.setStatus(401);
        List<Board> boardList = boardRepository.selectAll();
        return new ApiUtil<>(boardList); // MessageConverter
    }
    // RestController면서 Object일 때 MessageConverter가 발동 = JSON으로 바뀜
}
