package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardApiController {
    private final BoardRepository boardRepository;

    @GetMapping("/api/boards")
    public ApiUtil<?> findAll(HttpServletResponse response) {
        List<Board> boardList = boardRepository.selectAll();
        return new ApiUtil<>(boardList); // MessageConverter
        // RestController면서 return이 Object일 때 MessageConverter가 발동 = JSON으로 바뀜
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

    @PostMapping("/api/boards")
    public ApiUtil<?> write(@RequestBody BoardRequest.WriteDTO writeDTO) {
        boardRepository.insert(writeDTO);
        return new ApiUtil<>(null);
    }

    @PutMapping("/api/boards/{id}")
    public ApiUtil<?> updateById(@RequestBody BoardRequest.WriteDTO writeDTO, @PathVariable Integer id) {
        boardRepository.updateById(writeDTO, id);
        return new ApiUtil<>(null);
    }
}
