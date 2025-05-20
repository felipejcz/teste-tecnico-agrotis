package agrotis.com.teste_backend_java.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class AgrotisException extends RuntimeException {
    private String mensagem;

    public AgrotisException(String mensagem) {
        this.mensagem = mensagem;
    }

    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        pb.setTitle("Erro interno");
        pb.setDetail(mensagem);
        return pb;
    }
}
