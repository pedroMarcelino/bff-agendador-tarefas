package com.marcelino.bffagendadortarefas.controller;


import com.marcelino.bffagendadortarefas.business.UsuarioService;
import com.marcelino.bffagendadortarefas.business.dto.in.EnderecoDTORequest;
import com.marcelino.bffagendadortarefas.business.dto.in.LoginRequest;
import com.marcelino.bffagendadortarefas.business.dto.in.TelefoneDTORequest;
import com.marcelino.bffagendadortarefas.business.dto.in.UsuarioDTORequest;
import com.marcelino.bffagendadortarefas.business.dto.out.EnderecoDTOResponse;
import com.marcelino.bffagendadortarefas.business.dto.out.TelefoneDTOResponse;
import com.marcelino.bffagendadortarefas.business.dto.out.UsuarioDTOResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuario", description = "Cadatro e login de usuário")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    @Operation(summary = "Salvar Usuário", description = "Cria um novo usuário")
    @ApiResponse(responseCode = "200", description = "Usuario salvo com sucesso")
    @ApiResponse(responseCode = "400", description = "Usuario já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDTOResponse> salvaUsuario(@RequestBody UsuarioDTORequest usuarioDTO){
        return ResponseEntity.ok(usuarioService.salvaUsuario(usuarioDTO));
    }

    @Operation(summary = "Login de Usuario", description = "Faz o login de usuarios")
    @ApiResponse(responseCode = "200", description = "Usuario logado com sucesso")
    @ApiResponse(responseCode = "400", description = "Credenciais invalidas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest usuarioDTO){
        return usuarioService.loginUsuario(usuarioDTO);
    }

    @GetMapping
    @Operation(summary = "Busca usuario por email",
            description = "Busca dados do usuário. ")
    @ApiResponse(responseCode = "200", description = "Usuario encontrado")
    @ApiResponse(responseCode = "404", description = "Usuario não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDTOResponse> buscaUsuarioPorEmail(@RequestParam("email") String email,
                                                                   @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok( usuarioService.buscarUsuarioPorEmail(email, token));
    }

    @Operation(summary = "Deleta Usuario",
            description = "Deleta Usuario por id")
    @ApiResponse(responseCode = "200", description = "Usuario deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuario nao encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deletaUsuarioPorEmail(@PathVariable String email,
                                                      @RequestHeader(name = "Authorization", required = false) String token){
        usuarioService.deletaUsuarioPorEmail(email, token);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Atualizar Usuário",
            description = "Atualiza dados de usuário")
    @ApiResponse(responseCode = "200", description = "Usuario atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuario não cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @PutMapping
    public ResponseEntity<UsuarioDTOResponse> atualizaDadosUsuario(@RequestBody UsuarioDTORequest dto,
                                                                   @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.atualizaDadosUsuario(token, dto));
    }

    @Operation(summary = "Atualiza endereco usuário",
            description = "Atualiza endereco usuário")
    @ApiResponse(responseCode = "200", description = "Endereco atualizado com sucesso")
    @ApiResponse(responseCode = "400", description = "Endereco já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @PutMapping("/endereco")
    public ResponseEntity<EnderecoDTOResponse> atualizaEndereco(@RequestBody EnderecoDTORequest dto, @RequestParam("id") Long id,
                                                                @RequestHeader(name = "Authorization", required = false) String token){
        return  ResponseEntity.ok(usuarioService.autualizaEndereco(id, dto, token));
    }

    @Operation(summary = "Atualiza telefone usuário",
            description = "Atualiza telefone usuário")
    @ApiResponse(responseCode = "200", description = "Telefone atualizado com sucesso")
    @ApiResponse(responseCode = "400", description = "Telefone já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @PutMapping("/telefone")
    public ResponseEntity<TelefoneDTOResponse> atualizaTelefone(@RequestBody TelefoneDTORequest dto, @RequestParam("id") Long id,
                                                                @RequestHeader(name = "Authorization", required = false) String token){
        return  ResponseEntity.ok(usuarioService.autalizaTelefone(id, dto, token));
    }

    @Operation(summary = "Cadastra endereco usuário",
            description = "Cadastra endereco usuário")
    @ApiResponse(responseCode = "200", description = "Endereco cadastrado com sucesso")
    @ApiResponse(responseCode = "400", description = "Endereco já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @PostMapping("/endereco")
    public ResponseEntity<EnderecoDTOResponse> cadastraEndereco(@RequestBody EnderecoDTORequest dto,
                                                                @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.cadastraEndereco(token, dto));
    }

    @Operation(summary = "Cadastra telefone usuário",
            description = "Cadastra telefone usuário")
    @ApiResponse(responseCode = "200", description = "telefone cadastrado com sucesso")
    @ApiResponse(responseCode = "400", description = "telefone já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @PostMapping("/telefone")
    public ResponseEntity<TelefoneDTOResponse> cadastraTelefone(@RequestBody TelefoneDTORequest dto,
                                                                @RequestHeader(name = "Authorization", required = false) String token){
        return  ResponseEntity.ok(usuarioService.cadastraTelefone(token, dto));
    }
}
