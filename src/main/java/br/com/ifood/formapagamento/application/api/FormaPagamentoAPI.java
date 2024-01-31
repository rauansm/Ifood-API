package br.com.ifood.formapagamento.application.api;

import br.com.ifood.security.annotations.CheckSecurity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/forma-pagamento")
public interface FormaPagamentoAPI {

    @CheckSecurity.FormasPagamento.PodeEditar
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    FormaPagamentoResponse postFormaPagamento(@Valid @RequestBody FormaPagamentoRequest formaPagamentoRequest);

    @CheckSecurity.FormasPagamento.PodeConsultar
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    List<FormaPagamentoResponse> getTodasFormasPagamento();

    @CheckSecurity.FormasPagamento.PodeConsultar
    @GetMapping("/{idPagamento}")
    @ResponseStatus(code = HttpStatus.OK)
    FormaPagamentoResponse getFormaPagamentoAtravesId(@PathVariable Long idPagamento);

    @CheckSecurity.FormasPagamento.PodeEditar
    @DeleteMapping("/{idPagamento}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void deletaFormaPagamentoAtravesId(@PathVariable Long idPagamento);

    @CheckSecurity.FormasPagamento.PodeEditar
    @PutMapping("/{idPagamento}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void putAlteraFormaPagamento(@PathVariable Long idPagamento, @Valid @RequestBody FormaPagamentoRequest formaPagamentoRequest);
}
