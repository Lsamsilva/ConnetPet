package projetoFinal.conectPet.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import projetoFinal.conectPet.domain.dto.DoacaoResponseDTO;
import projetoFinal.conectPet.domain.entity.DoacaoEntity;
import projetoFinal.conectPet.exception.DoacaoNaoEncontradaException;
import projetoFinal.conectPet.repository.DoacaoRepository;

import java.io.File;
import java.io.IOException;

@Service
public class DoacoesService {

    //caminho do destino das imagens
    private final String FOLDER_PATH="c://Users/Acer/Fotos/";
    private final DoacaoRepository repository;

    ObjectMapper objectMapper = new ObjectMapper();
    public DoacoesService (final DoacaoRepository repository){this.repository = repository;}

    public DoacaoResponseDTO criarDoacao (MultipartFile image, String jsonData) throws IOException {

        //caminho do destino com o arquivo
        String filePath = FOLDER_PATH+image.getOriginalFilename();
        DoacaoResponseDTO doacaoRequest = objectMapper.readValue(jsonData, DoacaoResponseDTO.class);
        doacaoRequest.setImagem(filePath);

        var novaDoacao = new DoacaoEntity();
        novaDoacao.setNome(doacaoRequest.getNome());
        novaDoacao.setEspecie(doacaoRequest.getEspecie());
        novaDoacao.setIdade(doacaoRequest.getIdade());
        novaDoacao.setSexo(doacaoRequest.getSexo());
        novaDoacao.setEmail(doacaoRequest.getEmail());
        novaDoacao.setImagem(doacaoRequest.getImagem());
        novaDoacao.setUsuario(doacaoRequest.getUsuario());

        var doacaoSalva = repository.save(novaDoacao);

        //salva imagem no caminho local
        image.transferTo(new File(filePath));

        return new DoacaoResponseDTO(
                doacaoSalva.getId(),
                doacaoSalva.getNome(),
                doacaoSalva.getEspecie(),
                doacaoSalva.getIdade(),
                doacaoSalva.getSexo(),
                doacaoSalva.getEmail(),
                doacaoSalva.getImagem(),
                doacaoSalva.getUsuario()
        );

    }

    public DoacaoResponseDTO buscarPorId (Integer idDoacao){

        var doacaoEncontrada = repository.findById(idDoacao);

        if (doacaoEncontrada.isEmpty()){
            throw new DoacaoNaoEncontradaException();
        }

        var doacaoSalva = doacaoEncontrada.get();
        return new DoacaoResponseDTO(
                doacaoSalva.getId(),
                doacaoSalva.getNome(),
                doacaoSalva.getEspecie(),
                doacaoSalva.getIdade(),
                doacaoSalva.getSexo(),
                doacaoSalva.getEmail(),
                doacaoSalva.getImagem(),
                doacaoSalva.getUsuario()
        );
    }

    public DoacaoResponseDTO buscarPorNome (String nome){

        try{
            var doacaoEncontrada = repository.findByNome(nome);

            return new DoacaoResponseDTO(
                    doacaoEncontrada.getId(),
                    doacaoEncontrada.getNome(),
                    doacaoEncontrada.getEspecie(),
                    doacaoEncontrada.getIdade(),
                    doacaoEncontrada.getSexo(),
                    doacaoEncontrada.getEmail(),
                    doacaoEncontrada.getImagem(),
                    doacaoEncontrada.getUsuario()
            );
        }catch (DoacaoNaoEncontradaException e){
            throw new DoacaoNaoEncontradaException();
        }
    }

    public DoacaoResponseDTO atualizarDoacao (Integer idDoacao , MultipartFile image, String jsonData)
            throws IOException{

        //caminho do destino com o arquivo
        String filePath = FOLDER_PATH+image.getOriginalFilename();
        DoacaoResponseDTO doacaoRequest = objectMapper.readValue(jsonData, DoacaoResponseDTO.class);
        doacaoRequest.setImagem(filePath);

        var doacaoEncontrada = repository.findById(idDoacao);

        if(doacaoEncontrada.isEmpty()){
            throw new DoacaoNaoEncontradaException();
        }

        var doacaoAtualizada = doacaoEncontrada.get();
        doacaoAtualizada.setNome(doacaoRequest.getNome());
        doacaoAtualizada.setEspecie(doacaoRequest.getEspecie());
        doacaoAtualizada.setIdade(doacaoRequest.getIdade());
        doacaoAtualizada.setSexo(doacaoRequest.getSexo());
        doacaoAtualizada.setEmail(doacaoRequest.getEmail());
        doacaoAtualizada.setImagem(doacaoRequest.getImagem());

        //salva imagem no caminho local
        image.transferTo(new File(filePath));

        var doacaoSalva = repository.save(doacaoAtualizada);

        return new DoacaoResponseDTO(
                doacaoSalva.getId(),
                doacaoSalva.getNome(),
                doacaoSalva.getEspecie(),
                doacaoSalva.getIdade(),
                doacaoSalva.getSexo(),
                doacaoSalva.getEmail(),
                doacaoSalva.getImagem(),
                doacaoSalva.getUsuario()
        );
    }


}
