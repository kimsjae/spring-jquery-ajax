package shop.mtcoding.blog.board;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ApiUtil<T> {
    private Integer status; // 200, 400, 404, 405
    private String msg; // 실패했을 때 줄 메세지
    private T body; // 제네릭타입

    public ApiUtil(T body) {
        this.status = 200;
        this.msg = "성공";
        this.body = body;
    }

    public ApiUtil(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
        this.body = null;
    }
}
