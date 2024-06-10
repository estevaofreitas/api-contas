package br.com.nextcomputing.api.contas.application.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.nextcomputing.api.contas.application.rest.dto.ContaCreateRequest;
import br.com.nextcomputing.api.contas.application.rest.dto.ContaFiltroRequest;
import br.com.nextcomputing.api.contas.application.rest.dto.ContaResponse;
import br.com.nextcomputing.api.contas.application.rest.dto.ContaUpdateRequest;
import br.com.nextcomputing.api.contas.application.rest.dto.PagamentoFiltroRequest;
import br.com.nextcomputing.api.contas.application.rest.dto.PagamentoFiltroResponse;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@OpenAPIDefinition(info = @Info(title = "API Contas", description = "Projeto API Contas", version = "0.0.1", contact = @Contact(name = "Estevão Freitas", email = "estevaofreitas@gmail.com", url = "https://github.com/estevaofreitas"), license = @License(name = "GPL v3", url = "https://www.gnu.org/licenses/gpl-3.0.pt-br.html")))
@RestController
@RequestMapping("/api/conta")
public interface ContaApi {

        @Operation(summary = "Criação de conta")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Conta encontrada", content = {
                                        @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ContaResponse.class)) }) })
        @PutMapping
        @ResponseStatus(HttpStatus.OK)
        public ContaResponse create(@RequestBody ContaCreateRequest request);

        @Operation(summary = "Atualização da conta")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Conta encontrada", content = {
                                        @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ContaResponse.class)) }),
                        @ApiResponse(responseCode = "400", description = "Id da conta inválido", content = @Content),
                        @ApiResponse(responseCode = "404", description = "Conta não encontrada", content = @Content) })
        @PatchMapping
        @ResponseStatus(HttpStatus.OK)
        public ContaResponse update(@RequestBody ContaUpdateRequest request);

        @Operation(summary = "Atualização da situação da conta pelo id da conta")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Conta encontrada", content = {
                                        @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ContaResponse.class)) }),
                        @ApiResponse(responseCode = "400", description = "Id da conta inválido", content = @Content),
                        @ApiResponse(responseCode = "404", description = "Conta não encontrada", content = @Content) })
        @PatchMapping("/{id}/situacao/{situacao}")
        @ResponseStatus(HttpStatus.OK)
        public ContaResponse updateSituacao(
                        @Parameter(description = "Id da conta a ser procurada") @PathVariable long id,
                        @Parameter(description = "Nova situação da conta") @PathVariable String situacao);

        @Operation(summary = "Recupera uma conta pelo seu id")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Conta encontrada", content = {
                                        @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ContaResponse.class)) }),
                        @ApiResponse(responseCode = "400", description = "Id da conta inválido", content = @Content),
                        @ApiResponse(responseCode = "404", description = "Conta não encontrada", content = @Content) })
        @GetMapping("/{id}")
        @ResponseStatus(HttpStatus.OK)
        public ContaResponse findById(@Parameter(description = "Id da conta a ser procurada") @PathVariable long id);

        @Operation(summary = "Lista todas as contas pelo filtro")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Lista de contas encontradas", content = {
                                        @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = List.class)) }),
                        @ApiResponse(responseCode = "404", description = "Nenhuma conta encontrada", content = @Content) })
        @PostMapping
        @ResponseStatus(HttpStatus.OK)
        public List<ContaResponse> filterConta(
                        @Parameter(description = "Número da página") @RequestParam(required = false) Integer pageNumber,
                        @Parameter(description = "Tamanho da página") @RequestParam(required = false) Integer pageSize,
                        @RequestBody ContaFiltroRequest request);

        @Operation(summary = "Recupera a soma de todos os pagamentos pelo filtro")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Lista de contas encontradas", content = {
                                        @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PagamentoFiltroResponse.class)) }),
                        @ApiResponse(responseCode = "404", description = "Nenhuma conta encontrada", content = @Content) })
        @PostMapping("/total")
        @ResponseStatus(HttpStatus.OK)
        public PagamentoFiltroResponse filterPagamento(@RequestBody PagamentoFiltroRequest request);

        @Operation(summary = "Importação de contas por um arquivo csv")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Lista de contas encontradas", content = {
                                        @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE) }) })

        @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public void importFile(@RequestParam("arquivo") MultipartFile file);

}