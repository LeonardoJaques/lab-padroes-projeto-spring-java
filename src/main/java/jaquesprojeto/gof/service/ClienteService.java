package jaquesprojeto.gof.service;

import jaquesprojeto.gof.model.Cliente;
import org.springframework.stereotype.Service;

/**
 * Interface que define o padrao <b>Strategy</b> no dominio de clientes.
 * Com isso, se necessario, podemos ter multiplas implementacoes para a mesma interface.
 *
 * @author leonardojaques
 * @see <a href="https://refactoring.guru/pt-br/design-patterns/strategy">Padrao Strategy</a>
 */
@Service
public interface ClienteService {
		
		Iterable<Cliente> buscarTodos();
		
		Cliente buscarPorId(Long id);
		
		void inserir(Cliente cliente);
		
		void atualizar(Long id, Cliente cliente);
		
		void deletar(Long id);
		
}
