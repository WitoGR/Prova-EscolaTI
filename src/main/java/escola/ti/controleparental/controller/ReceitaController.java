package escola.ti.controleparental.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import escola.ti.controleparental.model.IngredienteModel;
import escola.ti.controleparental.model.ReceitaModel;
import escola.ti.controleparental.model.dto.IngredienteDTO;
import escola.ti.controleparental.model.dto.ReceitaDTO;
import escola.ti.controleparental.model.dto.ReceitasViewDTO;
import escola.ti.controleparental.model.dto.RemoveIngredienteDTO;
import escola.ti.controleparental.model.dto.RemoveReceitaDTO;
import escola.ti.controleparental.model.dto.UpdateReceitaDTO;
import escola.ti.controleparental.repository.IngredienteRepository;
import escola.ti.controleparental.repository.ReceitaRepository;

@RestController
@RequestMapping("/")
public class ReceitaController {
    
    @Autowired 
    private ReceitaRepository receitaRepository;

    @Autowired
    private IngredienteRepository ingredienteRepository;

    @PostMapping(path="/add-receita")
    public ResponseEntity<String> addNewReceita(@RequestBody ReceitaDTO body){
        ReceitaModel receita = new ReceitaModel();

        receita.setNome(body.getNome());
        receita.setTempoPreparo(body.getTempoPreparo());
        receita.setCustoAproximando(body.getCustoAproximado());

        receitaRepository.save(receita);

        return new ResponseEntity<String>("Receita criada com sucesso...", null, 200);
    }

    @PostMapping(path="/add-ingrediente")
    public ResponseEntity<String> addNewIngrediente(@RequestBody IngredienteDTO body){
        IngredienteModel ingrediente = new IngredienteModel();

        System.out.println(body.getNome());

        ingrediente.setNome(body.getNome());

        for(ReceitaModel r : receitaRepository.findAll()){

            if(body.getNomeReceita().equals(r.getNome())){

                ingrediente.setIdReceita(r.getIdReceita());
                ingredienteRepository.save(ingrediente);

                return new ResponseEntity<String>("Ingrediente adicionado com sucesso...", null, 200);
            }
        }
        return new ResponseEntity<String>("Ingrediente não criado", null, 400);
    }

    @GetMapping(path="view-receitas")
    public ResponseEntity<ArrayList<ReceitasViewDTO>> viewAllReceitas(){
        ArrayList<ReceitasViewDTO> resposta = new ArrayList<>();
       
        for(ReceitaModel r : receitaRepository.findAll()){
            ReceitasViewDTO receitasViewDTO = new ReceitasViewDTO();

            receitasViewDTO.setNome(r.getNome());
            receitasViewDTO.setTempoPreparo(r.getTempoPreparo());
            receitasViewDTO.setCustoAproximado(r.getCustoAproximando());
            

            for(IngredienteModel i : ingredienteRepository.findAll()){
                if(i.getIdReceita().equals(r.getIdReceita()))
                    receitasViewDTO.addIngrediente(i.getNome());;
            }

            resposta.add(receitasViewDTO);
        }
        
        return new ResponseEntity<ArrayList<ReceitasViewDTO>>(resposta, null, 200);
    }

    @PostMapping(path="delete-receita")
    public ResponseEntity<String> removeReceita(@RequestBody RemoveReceitaDTO body){

        for(ReceitaModel r : receitaRepository.findAll()){
            if(body.getNome().equals(r.getNome())){
                for(IngredienteModel i : ingredienteRepository.findAll()){
                    if(i.getIdReceita().equals(r.getIdReceita())){
                        ingredienteRepository.delete(i);
                    }
                }
                receitaRepository.delete(r);
                return new ResponseEntity<String>("Receita removida", null, 200);
            }
        }

        return new ResponseEntity<String>("Receita não removida", null, 400);
    }
    
    @PostMapping(path="delete-ingrediente")
    public ResponseEntity<String> deleteIngrediente(@RequestBody RemoveIngredienteDTO body){
        Integer idReceita = null;

        for(ReceitaModel r : receitaRepository.findAll()){
            if(body.getNomeReceita().equals(r.getNome()))
                idReceita = r.getIdReceita();
        }

        for(IngredienteModel i : ingredienteRepository.findAll()){
            if(i.getIdReceita().equals(idReceita))
            {
                if(i.getNome().equals(body.getNomeIngrediente())){

                    ingredienteRepository.delete(i);

                    return new ResponseEntity<String>("Ingrediente removido!", null, 200);
                }
            }
        }

        return new ResponseEntity<String>("Ingrediente não removido", null, 400);
    }

    @PostMapping(path="update-receita")
    public ResponseEntity<String> updateReceita(@RequestBody UpdateReceitaDTO body){
        ReceitaModel resposta = new ReceitaModel();
        Boolean encontrou = false;

        for(ReceitaModel r : receitaRepository.findAll()){
            if(body.getNome().equals(r.getNome()))
            {
                resposta = r;
                encontrou = true;
                break;
            }
        }

        if(encontrou){
            if(body.getNovoNome()!=null)
                resposta.setNome(body.getNovoNome());
            if(body.getNovoTempo()!= null)
                resposta.setTempoPreparo(body.getNovoTempo());
            if(body.getNovoValor()!=null)
                resposta.setCustoAproximando(body.getNovoValor());

            receitaRepository.save(resposta);

            return new ResponseEntity<String>("Receita atualizada!", null, 400);
        }

        return new ResponseEntity<String>("Receita não atualizada", null, 400);
    }
    
}
