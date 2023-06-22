package model.gerador;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConversorData {

	public static String formatoDataBrasil = "dd/MM/yyyy HH:mm";

	public static String formatarDataPadraoBrasil(LocalDateTime data) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatoDataBrasil);
		return formatter.format(data);
	}
}
