package br.com.ifood.fotoproduto.application.api.dto;

import br.com.ifood.core.validation.FileContentType;
import br.com.ifood.core.validation.FileSize;
import lombok.Value;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
public class FotoProdutoRequest {
    @NotNull
    @FileSize(max = "500KB")
    @FileContentType(allowed = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    private MultipartFile arquivo;
    @NotBlank
    private String descricao;
}
