package de.tr7zw;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class ExampleUsage {

    public static void main(String[] args) throws IOException {
	File output = new File("emoji.txt");

	byte[] input = Files.readAllBytes(new File("input.png").toPath());
	String emoji = BaseEmojiUtil.convertToEmoji(input);

	try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(output), StandardCharsets.UTF_8)) {
	    writer.write(emoji);
	}

	byte[] afterEmoji = BaseEmojiUtil.convertFromEmoji(emoji);
	Files.write(new File("output.png").toPath(), afterEmoji);
    }

}
