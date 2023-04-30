package dio.padroesprojetos.service;

import java.util.List;

import dio.padroesprojetos.model.Cliente;

public interface ClienteService {
	
	List<Cliente> buscarTodos();
	Cliente buscarPorId(Long id);
	void inserir(Cliente cliente);
	void atualizar(Long id, Cliente cliente);
	void deleatar(Long id);

}
