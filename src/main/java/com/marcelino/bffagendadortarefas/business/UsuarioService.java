package com.marcelino.bffagendadortarefas.business;

import com.marcelino.bffagendadortarefas.business.dto.in.EnderecoDTORequest;
import com.marcelino.bffagendadortarefas.business.dto.in.LoginRequest;
import com.marcelino.bffagendadortarefas.business.dto.in.TelefoneDTORequest;
import com.marcelino.bffagendadortarefas.business.dto.in.UsuarioDTORequest;
import com.marcelino.bffagendadortarefas.business.dto.out.EnderecoDTOResponse;
import com.marcelino.bffagendadortarefas.business.dto.out.TelefoneDTOResponse;
import com.marcelino.bffagendadortarefas.business.dto.out.UsuarioDTOResponse;
import com.marcelino.bffagendadortarefas.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient client;

    public UsuarioDTOResponse salvaUsuario (UsuarioDTORequest usuarioDTO){
        return client.salvaUsuario(usuarioDTO);
    }

    public String loginUsuario(LoginRequest usuarioDTO){
        return  client.login(usuarioDTO);
    }

    public UsuarioDTOResponse buscarUsuarioPorEmail(String email, String token){
        return  client.buscaUsuarioPorEmail(email, token);
    }

    public void deletaUsuarioPorEmail(String email, String token){
        client.deletaUsuarioPorEmail(email, token);
    }

    public UsuarioDTOResponse atualizaDadosUsuario(String token, UsuarioDTORequest dto){
        return client.atualizaDadosUsuario(dto, token);
    }

    public EnderecoDTOResponse autualizaEndereco(Long idEndereco, EnderecoDTORequest enderecoDTO, String token){
        return client.atualizaEndereco(enderecoDTO, idEndereco, token);
    }


    public TelefoneDTOResponse autalizaTelefone(Long idTelefone, TelefoneDTORequest telefoneDTO, String token){
        return  client.atualizaTelefone(telefoneDTO, idTelefone, token);
    }


    public EnderecoDTOResponse cadastraEndereco (String token, EnderecoDTORequest dto ){
        return client.cadastraEndereco(dto, token);
    }

    public TelefoneDTOResponse cadastraTelefone (String token, TelefoneDTORequest dto ){
        return client.cadastraTelefone(dto, token);
    }
}
