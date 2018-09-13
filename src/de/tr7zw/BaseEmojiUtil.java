package de.tr7zw;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map.Entry;

public class BaseEmojiUtil {

    private static HashMap<String, String> mapping = new HashMap<>();

    static {
	String emojis = "😋😛😜🤗🤔🤐😐😏😒🙄😬😪😷🤒🤕🤢😵🤠🤓😎😕🙁😲😯😳😦😧😨😰😥😢😭😱😖😣😞😓😩😫😡😠👿💩🙈🙉🙊💦💣👌👍🤳🦄🍌🍍🍆🥑🥒🥜🍕🌭🥓🌯🍺🥃🍾";
	int offset = 0;
	for (int i = 'A'; i <= 'Z'; i++) {
	    String replacement = "" + (char) i;
	    if (offset < emojis.codePointCount(0, emojis.length()) * 2) {
		replacement = new StringBuilder().appendCodePoint(emojis.codePointAt(offset)).toString();
	    }
	    mapping.put("" + ((char) i), replacement);
	    offset += 2;
	}
	for (int i = 'a'; i <= 'z'; i++) {
	    String replacement = "" + (char) i;
	    if (offset < emojis.codePointCount(0, emojis.length()) * 2) {
		replacement = new StringBuilder().appendCodePoint(emojis.codePointAt(offset)).toString();
	    }
	    mapping.put("" + ((char) i), replacement);
	    offset += 2;
	}
	for (int i = '0'; i <= '9'; i++) {
	    String replacement = "" + (char) i;
	    if (offset < emojis.codePointCount(0, emojis.length()) * 2) {
		replacement = new StringBuilder().appendCodePoint(emojis.codePointAt(offset)).toString();
	    }
	    mapping.put("" + ((char) i), replacement);
	    offset += 2;
	}
	mapping.put("=", new StringBuilder().appendCodePoint(emojis.codePointAt(offset)).toString());
	offset += 2;
	mapping.put("+", new StringBuilder().appendCodePoint(emojis.codePointAt(offset)).toString());
	offset += 2;
	mapping.put("/", new StringBuilder().appendCodePoint(emojis.codePointAt(offset)).toString());

    }

    public static String convertToEmoji(byte[] data) {
	String base64 = Base64.getEncoder().encodeToString(data);
	for (Entry<String, String> entry : mapping.entrySet())
	    base64 = base64.replace(entry.getKey(), entry.getValue());
	return base64;
    }

    public static byte[] convertFromEmoji(String emoji) {
	for (Entry<String, String> entry : mapping.entrySet())
	    emoji = emoji.replace(entry.getValue(), entry.getKey());
	return Base64.getDecoder().decode(emoji);
    }

}
