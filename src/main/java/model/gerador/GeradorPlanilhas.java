package model.gerador;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import model.dto.FuncionarioDTO;
import model.dto.PrestacaoDTO;

public class GeradorPlanilhas {

	public String geradorPlanilhaServicos(List<PrestacaoDTO> prestacoes, String caminho) {
		HSSFWorkbook planilha = new HSSFWorkbook();

		// 1 - Cria uma aba na planilha
		HSSFSheet abaPlanilha = planilha.createSheet("Serviços");

		// 2- Cria o cabeçalho
		HSSFRow linhaCabecalho = abaPlanilha.createRow(0);
		linhaCabecalho.createCell(0).setCellValue("Nome");
		linhaCabecalho.createCell(1).setCellValue("Cargo");
		linhaCabecalho.createCell(2).setCellValue("Sala");
		linhaCabecalho.createCell(3).setCellValue("Data");
		linhaCabecalho.createCell(4).setCellValue("Serviço Realizado");
		linhaCabecalho.createCell(5).setCellValue("Ocorrência");

		// 3- Cria as linhas de resultado
		int contadorLinhas = 1;
		for (PrestacaoDTO p : prestacoes) {
			HSSFRow novaLinha = abaPlanilha.createRow(contadorLinhas);
			novaLinha.createCell(0).setCellValue(p.getNomeFuncionario());
			novaLinha.createCell(1).setCellValue(p.getNomeCargo());
			novaLinha.createCell(2).setCellValue(p.getNumeroSala());
			novaLinha.createCell(3).setCellValue(p.getDataInicio() + p.getDataFim());
			novaLinha.createCell(4).setCellValue(p.getServico());
			novaLinha.createCell(5).setCellValue(p.getOcorrencia());

			contadorLinhas++;
		}

		// 4- Ajusta o tamanho de todas as colunas conforme a largura do conteúdo
		for (int i = 0; i < contadorLinhas; i++) {
			abaPlanilha.autoSizeColumn(i);
		}

		return salvarNoDisco(planilha, caminho);
	}

	public String geradorPlanilhaFuncionarios(List<FuncionarioDTO> funcionarios, String caminho) {
		HSSFWorkbook planilha = new HSSFWorkbook();

		// 1 - Cria uma aba na planilha
		HSSFSheet abaPlanilha = planilha.createSheet("Funcionários");

		// 2- Cria o cabeçalho
		HSSFRow linhaCabecalho = abaPlanilha.createRow(0);
		linhaCabecalho.createCell(0).setCellValue("Nome");
		linhaCabecalho.createCell(1).setCellValue("Cargo");
		linhaCabecalho.createCell(2).setCellValue("Data Desligamento");

		// 3- Cria as linhas de resultado
		int contadorLinhas = 1;
		for (FuncionarioDTO f : funcionarios) {
			HSSFRow novaLinha = abaPlanilha.createRow(contadorLinhas);
			novaLinha.createCell(0).setCellValue(f.getNome());
			novaLinha.createCell(1).setCellValue(f.getCargo());
			novaLinha.createCell(2).setCellValue(f.getDataDesligamento());

			contadorLinhas++;
		}

		// 4- Ajusta o tamanho de todas as colunas conforme a largura do conteúdo
		for (int i = 0; i < contadorLinhas; i++) {
			abaPlanilha.autoSizeColumn(i);
		}

		return salvarNoDisco(planilha, caminho);
	}

	// Escreve o arquivo em disco no caminho informado
	private String salvarNoDisco(HSSFWorkbook planilha, String caminho) {
		String mensagem = "";
		FileOutputStream saida = null;
		String extensao = ".xls";

		try {
			saida = new FileOutputStream(new File(caminho + extensao));
			planilha.write(saida);
			mensagem = "Planilha gerada com sucesso!";
		} catch (FileNotFoundException e) {
			mensagem = "Erro ao tentar salvar planilha (sem acesso): " + caminho + extensao;
			System.out.println("Causa: " + e.getMessage());
		} catch (IOException e) {
			mensagem = "Erro de I/O ao tentar salvar planilha em: " + caminho + extensao;
			System.out.println("Causa: " + e.getMessage());
		} finally {
			if (saida != null) {
				try {
					saida.close();
					planilha.close();
				} catch (IOException e) {
					mensagem = "Erro ao tentar salvar planilha em: " + caminho + extensao;
					System.out.println("Causa: " + e.getMessage());
				}
			}
		}

		return mensagem;

	}
}
