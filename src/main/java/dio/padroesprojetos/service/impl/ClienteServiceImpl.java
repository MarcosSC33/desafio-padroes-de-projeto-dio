package dio.padroesprojetos.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dio.padroesprojetos.model.Cliente;
import dio.padroesprojetos.model.ClienteRepository;
import dio.padroesprojetos.model.Endereco;
import dio.padroesprojetos.model.EnderecoRepository;
import dio.padroesprojetos.service.ClienteService;
import dio.padroesprojetos.service.ViaCepService;

@Service
public class ClienteServiceImpl implements ClienteService{

	@Autowired
	EnderecoRepository enderecoRepository;
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	ViaCepService viaCepService;
	
	@Override
	public List<Cliente> buscarTodos() {
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
	public void deleatar(Long id) {
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
