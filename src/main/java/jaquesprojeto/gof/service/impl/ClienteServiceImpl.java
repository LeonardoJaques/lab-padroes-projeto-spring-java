package jaquesprojeto.gof.service.impl;

import jaquesprojeto.gof.model.Cliente;
import jaquesprojeto.gof.model.Endereco;
import jaquesprojeto.gof.repository.ClienteRepository;
import jaquesprojeto.gof.repository.EnderecoRepository;
import jaquesprojeto.gof.service.ClienteService;
import jaquesprojeto.gof.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementação da <b>Service</b> {@link ClienteService}, qual pode ser
 * injetada pelo Spring (Via @link Autowired}. Com isso, como essa classe é
 * um @link Service ela é tratada como um <b>Singleton</b> pelo Spring.
 *
 * @author leonardojaques
 */
@Service
public class ClienteServiceImpl implements ClienteService {
		// Singleton: Injetar os componentes do Spring com @Autowired.
		@Autowired
		private ClienteRepository clienteRepository;
		@Autowired
		private EnderecoRepository enderecoRepository;
		@Autowired
		private ViaCepService viaCepService;
		
		@Override
		public Iterable<Cliente> buscarTodos() {
				return clienteRepository.findAll();
		}
		
		@Override
		public Cliente buscarPorId(Long id) {
				Optional<Cliente> cliente = clienteRepository.findById(id);
				return cliente.get();
		}
		
		
		@Override
		public void inserir(Cliente cliente) {
				salvarClienteComCep(cliente);
		}
		
		
		@Override
		public void atualizar(Long id, Cliente cliente) {
				Optional<Cliente> clienteBd = clienteRepository.findById(id);
				if (clienteBd.isPresent()) {
						salvarClienteComCep(cliente);
				}
				
		}
		
		@Override
		public void deletar(Long id) {
				clienteRepository.deleteById(id);
		}
		
		private void salvarClienteComCep(Cliente cliente) {
				String cep = cliente.getEndereco().getCep();
				Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
						Endereco novoEndereco = viaCepService.consultarCep(cep);
						enderecoRepository.save(novoEndereco);
						return novoEndereco;
				});
				cliente.setEndereco(endereco);
				clienteRepository.save(cliente);
		}
}
