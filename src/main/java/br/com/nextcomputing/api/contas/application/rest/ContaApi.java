package br.com.nextcomputing.api.contas.application.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.nextcomputing.api.contas.application.rest.dto.ContaDto;
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

@OpenAPIDefinition(info = @Info(title = "Human cloning API",
                                description = "API for creating clone who will fight in the clones wars",
                                version = "2.0",
                                contact = @Contact(
                                        name = "LE TUTOUR Erwan",
                                        email = "erwanletutour.elt@gmail.com",
                                        url = "https://github.com/ErwanLT"
                                ),
                                license = @License(
                                        name = "MIT Licence",
                                        url = "https://opensource.org/licenses/mit-license.php"
                                )
))
@RequestMapping("/api/conta")
public interface ContaApi {

    @Operation(summary = "Recupera uma conta pelo seu id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Conta encontrada", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ContaDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Id da conta inválido", content = @Content),
            @ApiResponse(responseCode = "404", description = "Conta não encontrada", content = @Content) })
    @GetMapping("/{id}")
    public ContaDto findById(@Parameter(description = "Id da conta a ser procurada") @PathVariable long id);

}