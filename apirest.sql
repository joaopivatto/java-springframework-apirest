-- phpMyAdmin SQL Dump
-- version 5.2.1deb3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Tempo de geração: 18/04/2025 às 20:16
-- Versão do servidor: 10.11.11-MariaDB-0ubuntu0.24.04.2
-- Versão do PHP: 8.3.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `apirest`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `person`
--

CREATE TABLE `person` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `birth_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `person`
--

INSERT INTO `person` (`id`, `name`, `gender`, `birth_date`) VALUES
(1, 'João Pivatto', 'Male', '2007-04-04');

-- --------------------------------------------------------

--
-- Estrutura para tabela `role`
--

CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `authority` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `role`
--

INSERT INTO `role` (`id`, `authority`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Estrutura para tabela `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `login` varchar(30) NOT NULL,
  `password` varchar(255) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `user`
--

INSERT INTO `user` (`id`, `login`, `password`, `name`) VALUES
(1, 'admin', '$2a$10$cQlHmoM35gve6iR.UIrNVuBT5xV5.GGWNaW/Tr8WvVm1XMG7PgWUm', 'Admin'),
(2, 'user', '$2a$10$QOexb9SUL1ey898iENCrDu0/esPlqWnQiU..ZoKSaP2DWlIxYfUfG', 'User'),
(3, 'joao', '$2a$10$0ikOyv8JjvLuvdKb8Ab/0OkYhbMGRBKcVJIOytGwIjh6CPc6xRP9S', 'João Guilherme Pivatto');

-- --------------------------------------------------------

--
-- Estrutura para tabela `user_authorities`
--

CREATE TABLE `user_authorities` (
  `user_id` int(11) NOT NULL,
  `authorities_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `user_authorities`
--

INSERT INTO `user_authorities` (`user_id`, `authorities_id`) VALUES
(1, 1),
(2, 2),
(3, 1);

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `person`
--
ALTER TABLE `person`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de tabela `role`
--
ALTER TABLE `role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de tabela `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
