CREATE TABLE pessoa(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	ativo BOOLEAN NOT NULL,
	logradouro VARCHAR(50),
	numero VARCHAR(30),
	complemento VARCHAR(50),
	bairro VARCHAR(30),
	cep VARCHAR(30),
	cidade VARCHAR(30),
	estado VARCHAR(30)
	
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

 
 INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado)  
 values ('João Silva', true, 'Rua Bernado de Melo', '110', 'Casa', 'Maraguape 1', '53444360', 'Paulista', 'PE');
 
 INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado)  
 values ('Marcos Silva', false, 'Rua do Machado', '303', 'Casa', 'Arruda', '52120120', 'Recife', 'PE');
 
 INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado)  
 values ('Isabella Alencastro', true, 'Rua Aquinopolis', '763', 'Casa', 'Agua fria', '52130026', 'Recife', 'PE');
 
  INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado)  
 values ('Abel Vieira', true, 'Rua do machado', '303', 'Casa A', 'Arruda', '52120120', 'Recife', 'PE');
 
  INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado)  
 values ('Glecio Santos', true, 'Avenida Antonio cabral de souza', '1720', 'Residencial algarve bloco 03 apt 403', 'Maraguape 1', '53444360', 'Paulista', 'PE');
 
  INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado)  
 values ('Maria josefina', false, 'Rua barão de Itmáraca', '250', 'Casa', 'Espinheiro', '53125036', 'Recife', 'PE');
 
  INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado)  
 values ('Josilene Rodrigues', true, 'Rua Aquinopolis', '763', 'Casa', 'Agua fria ', '52130026', 'Recife', 'PE');
 
   INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado)  
 values ('Cristiane Guimaraes ', false, 'Rua do machado', '303', 'Casa A', 'Arruda', '52120120', 'Recife', 'PE');
 
   INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado)  
 values ('Niklaus', true, 'Avenida Antonio cabral de souza', '1720', 'Residencial algarve bloco 03 apt 403', 'Maraguape 1', '53444360', 'Paulista', 'PE');
 
 
 
 
 
 
 
 
 
