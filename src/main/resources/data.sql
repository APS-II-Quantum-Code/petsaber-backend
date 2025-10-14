INSERT INTO tb_especie (nome, descricao)
VALUES ('Cachorro', 'Especie de cachorro');
INSERT INTO tb_especie (nome, descricao)
VALUES ('Gato', 'Especie de gato');

INSERT INTO tb_raca (nome, descricao, id_especie)
VALUES ('Golden Retriever', 'Raca de cachorro', 1);
INSERT INTO tb_raca (nome, descricao, id_especie)
VALUES ('Shih Tzu', 'Raca de cachorro', 1);
INSERT INTO tb_raca (nome, descricao, id_especie)
VALUES ('Siamese', 'Raca de gato', 2);

INSERT INTO tb_porte (nome, descricao)
VALUES ('Mini/Toy', 'Menos de 6 kg e até 28 cm de altura.');
INSERT INTO tb_porte (nome, descricao)
VALUES ('Pequeno', 'De 6 a 15 kg e entre 28 e 35 cm de altura.');
INSERT INTO tb_porte (nome, descricao)
VALUES ('Medio', 'De 15 a 25 kg e entre 35 e 49 cm de altura.');
INSERT INTO tb_porte (nome, descricao)
VALUES ('Grande', 'De 25 a 45 kg e entre 50 e 69 cm de altura.');
INSERT INTO tb_porte (nome, descricao)
VALUES ('Gigante', 'Acima de 45 kg e mais de 70 cm de altura');

-- Inserindo um Tutor default
INSERT INTO petsaber.tb_tutor (cpf, email, nome, senha)
VALUES (null, 'tutor@gmail.com', 'Tutor', '$2a$10$DYrdKkk2Mu9N80bdCGmThOvJI.WwBvTy9ylcJU/JTtTDQlJJWPI.e');

-- Consultor
INSERT INTO petsaber.tb_consultor (cpf, email, nome, senha)
VALUES (null, 'admin@gmail.com', 'Admin', '$2a$10$fs7zksa1JboaqmNI98JBk.frJ8G5q1xBp47G/yW1yKCmsbyOyZFw6');


-- Inserindo Trilhas (uma para cada raça)
INSERT INTO tb_trilha (nome, descricao, nivel, horas_totais, modulos_totais, id_raca)
VALUES ('Obediência Básica Golden', 'Treinamento para Golden Retriever', 'INICIANTE', 30, 3, 1),
       ('Adestramento Shih Tzu', 'Treinamento especializado para Shih Tzu', 'INICIANTE', 30, 3, 2),
       ('Treinamento Siamês', 'Treinamento para gatos Siameses', 'INICIANTE', 30, 3, 3);

-- Módulos para Trilha Golden Retriever
INSERT INTO tb_modulo (nome, descricao, duracao_horas, ordem, conteudo, id_trilha)
VALUES ('Socialização Golden', 'Fundamentos de socialização', 10.0, 1, 'Técnicas de socialização para Golden', 1),
       ('Obediência Golden', 'Comandos de obediência', 10.0, 2, 'Comandos básicos para Golden', 1),
       ('Brincadeiras Golden', 'Atividades recreativas', 10.0, 3, 'Brincadeiras específicas para Golden', 1),

-- Módulos para Trilha Shih Tzu
       ('Socialização Shih Tzu', 'Fundamentos de socialização', 10.0, 1, 'Técnicas de socialização para Shih Tzu', 2),
       ('Obediência Shih Tzu', 'Comandos de obediência', 10.0, 2, 'Comandos básicos para Shih Tzu', 2),
       ('Higiene Shih Tzu', 'Cuidados especiais', 10.0, 3, 'Cuidados com pelagem e higiene', 2),

-- Módulos para Trilha Siamês
       ('Socialização Siamês', 'Fundamentos de socialização', 10.0, 1, 'Técnicas de socialização para Siamês', 3),
       ('Caixa de Areia', 'Treinamento sanitário', 10.0, 2, 'Uso adequado da caixa de areia', 3),
       ('Arranhadores', 'Uso de arranhadores', 10.0, 3, 'Treinamento com arranhadores', 3);

-- Exercícios para Módulos Golden
INSERT INTO tb_exercicio (nome, descricao, pontuacao, id_modulo)
VALUES
-- Módulo 1 Golden
('Apresentação a Pessoas', 'Como apresentar seu Golden a visitantes', 10.0, 1),
('Contato com Outros Cães', 'Socialização com outros cães', 10.0, 1),
('Ambientes Novos', 'Adaptação a novos ambientes', 10.0, 1),

-- Módulo 2 Golden
('Comando Sentar', 'Treinamento do comando sentar', 10.0, 2),
('Comando Deitar', 'Treinamento do comando deitar', 10.0, 2),
('Comando Ficar', 'Treinamento do comando ficar', 10.0, 2),

-- Módulo 3 Golden
('Buscar Bolinha', 'Brincadeira de buscar', 10.0, 3),
('Cabo de Guerra', 'Brincadeira com corda', 10.0, 3),
('Procurar Petiscos', 'Caça ao tesouro', 10.0, 3);

-- Alternativas (4 para cada exercício - apenas exemplo dos primeiros exercícios)
INSERT INTO tb_alternativa (conteudo, correta, id_exercicio)
VALUES
-- Alternativas Exercício 1 (Apresentação a Pessoas)
('Manter o cão calmo e sentado', true, 1),
('Deixar o cão pular nas pessoas', false, 1),
('Oferecer petiscos para reforço positivo', false, 1),
('Forçar interação quando o cão estiver ansioso', false, 1),

-- Alternativas Exercício 2 (Contato com Outros Cães)
('Começar com distância segura', true, 2),
('Permitir aproximação gradual', false, 2),
('Soltar os cães imediatamente', false, 2),
('Ignorar sinais de desconforto', false, 2),

-- Alternativas Exercício 3 (Ambientes Novos)
('Permitir exploração livre', true, 3),
('Forçar entrada em áreas temidas', false, 3),
('Usar recompensas para associação positiva', false, 3),
('Ignorar sinais de medo', false, 3);


UPDATE tb_modulo
SET conteudo = 'Lorem ipsum dolor sit amet consectetur adipiscing elit. Quisque faucibus ex sapien vitae pellentesque sem placerat. In id cursus mi pretium tellus duis convallis. Tempus leo eu aenean sed diam urna tempor. Pulvinar vivamus fringilla lacus nec metus bibendum egestas. Iaculis massa nisl malesuada lacinia integer nunc posuere. Ut hendrerit semper vel class aptent taciti sociosqu. Ad litora torquent per conubia nostra inceptos himenaeos.

Lorem ipsum dolor sit amet consectetur adipiscing elit. Quisque faucibus ex sapien vitae pellentesque sem placerat. In id cursus mi pretium tellus duis convallis. Tempus leo eu aenean sed diam urna tempor. Pulvinar vivamus fringilla lacus nec metus bibendum egestas. Iaculis massa nisl malesuada lacinia integer nunc posuere. Ut hendrerit semper vel class aptent taciti sociosqu. Ad litora torquent per conubia nostra inceptos himenaeos.

Lorem ipsum dolor sit amet consectetur adipiscing elit. Quisque faucibus ex sapien vitae pellentesque sem placerat. In id cursus mi pretium tellus duis convallis. Tempus leo eu aenean sed diam urna tempor. Pulvinar vivamus fringilla lacus nec metus bibendum egestas. Iaculis massa nisl malesuada lacinia integer nunc posuere. Ut hendrerit semper vel class aptent taciti sociosqu. Ad litora torquent per conubia nostra inceptos himenaeos.

Lorem ipsum dolor sit amet consectetur adipiscing elit. Quisque faucibus ex sapien vitae pellentesque sem placerat. In id cursus mi pretium tellus duis convallis. Tempus leo eu aenean sed diam urna tempor. Pulvinar vivamus fringilla lacus nec metus bibendum egestas. Iaculis massa nisl malesuada lacinia integer nunc posuere. Ut hendrerit semper vel class aptent taciti sociosqu. Ad litora torquent per conubia nostra inceptos himenaeos.

Lorem ipsum dolor sit amet consectetur adipiscing elit. Quisque faucibus ex sapien vitae pellentesque sem placerat. In id cursus mi pretium tellus duis convallis. Tempus leo eu aenean sed diam urna tempor. Pulvinar vivamus fringilla lacus nec metus bibendum egestas. Iaculis massa nisl malesuada lacinia integer nunc posuere. Ut hendrerit semper vel class aptent taciti sociosqu. Ad litora torquent per conubia nostra inceptos himenaeos.

Lorem ipsum dolor sit amet consectetur adipiscing elit. Quisque faucibus ex sapien vitae pellentesque sem placerat. In id cursus mi pretium tellus duis convallis. Tempus leo eu aenean sed diam urna tempor. Pulvinar vivamus fringilla lacus nec metus bibendum egestas. Iaculis massa nisl malesuada lacinia integer nunc posuere. Ut hendrerit semper vel class aptent taciti sociosqu. Ad litora torquent per conubia nostra inceptos himenaeos.

Lorem ipsum dolor sit amet consectetur adipiscing elit. Quisque faucibus ex sapien vitae pellentesque sem placerat. In id cursus mi pretium tellus duis convallis. Tempus leo eu aenean sed diam urna tempor. Pulvinar vivamus fringilla lacus nec metus bibendum egestas. Iaculis massa nisl malesuada lacinia integer nunc posuere. Ut hendrerit semper vel class aptent taciti sociosqu. Ad litora torquent per conubia nostra inceptos himenaeos.

Lorem ipsum dolor sit amet consectetur adipiscing elit. Quisque faucibus ex sapien vitae pellentesque sem placerat. In id cursus mi pretium tellus duis convallis. Tempus leo eu aenean sed diam urna tempor. Pulvinar vivamus fringilla lacus nec metus bibendum egestas. Iaculis massa nisl malesuada lacinia integer nunc posuere. Ut hendrerit semper vel class aptent taciti sociosqu. Ad litora torquent per conubia nostra inceptos himenaeos.'
WHERE TRUE;