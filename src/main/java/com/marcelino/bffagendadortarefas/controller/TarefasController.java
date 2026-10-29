package com.marcelino.bffagendadortarefas.controller;


import com.marcelino.bffagendadortarefas.business.TarefasService;
import com.marcelino.bffagendadortarefas.business.dto.in.TarefasDTORequest;
import com.marcelino.bffagendadortarefas.business.dto.out.TarefasDTOResponse;
import com.marcelino.bffagendadortarefas.business.dto.out.UsuarioDTOResponse;
import com.marcelino.bffagendadortarefas.business.enums.StatusNotificacaoEnum;
import com.marcelino.bffagendadortarefas.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
@Tag(name="Tarefas", description = "Cadastra tarefas de usuario")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TarefasController {

    private final TarefasService tarefasService;

    @Operation(summary = "Cadastra tarefas usuário",
            description = "Cadastra tarefas usuário")
    @ApiResponse(responseCode = "200", description = "Tarefa cadastrado com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @PostMapping
    public ResponseEntity<TarefasDTOResponse> gravarTarefas(@RequestBody TarefasDTORequest dto,
                                                            @RequestHeader(value = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefasService.gravarTarefa(token ,dto));
    }

    @Operation(summary = "Busca tarefas",
            description = "Busca tarefas por periodo")
    @ApiResponse(responseCode = "200", description = "Tarefa encontradas com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @GetMapping("/eventos")
    public ResponseEntity<List<TarefasDTOResponse>> buscaListaDeTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader(name = "Authorization", required = false) String token ){
        return ResponseEntity.ok(tarefasService.buscaTarefasAgendadasPorPeriodo(dataInicial, dataFinal, token));
    }

    @Operation(summary = "busca tarefas - email",
            description = "busca tarefas por email")
    @ApiResponse(responseCode = "200", description = "Tarefa encontradas com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @GetMapping
    public ResponseEntity<List<TarefasDTOResponse>> buscaTarefasPorEmail(@RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefasService.buscaTarefasPorEmail(token));
    }

    @Operation(summary = "Deleta tarefas",
            description = "Deleta tarefas por ID")
    @ApiResponse(responseCode = "200", description = "Tarefa deletada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @DeleteMapping
    public ResponseEntity<Void> deletaTarefaPorId(@RequestParam("id") String id,
                                                  @RequestHeader(name = "Authorization", required = false) String token){

        tarefasService.deletarTarefaPorId(id, token);

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Altera status",
            description = "Altera o status da tarefa")
    @ApiResponse(responseCode = "200", description = "Status da tarefa encontradas com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @PatchMapping
    public ResponseEntity<TarefasDTOResponse> alteraStatusNotificacao(@RequestParam("status") StatusNotificacaoEnum status,
                                                                                 @RequestParam("id") String id,
                                                                                 @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefasService.alterarStatus(status, id, token));
    }

    @Operation(summary = "Altera tarefa",
            description = "Altera dados da tarefa cadastrada")
    @ApiResponse(responseCode = "200", description = "Tarefa alterada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @PutMapping
    public ResponseEntity<TarefasDTOResponse> updateTarefas(@RequestBody TarefasDTORequest dto,
                                                                       @RequestParam("id") String id,
                                                                       @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefasService.updateTarefas(dto, id, token));
    }
}
