package com.jenspersson.mallgroda.proc;

import com.jenspersson.mallgroda.runtime.Out;

public class LineFragment extends Fragment {

    private String text;

    public LineFragment(String text) {
        this.text = text;
    }

    @Override
    public void write(Indent indent, Out out) {
        if (text.isEmpty()) {
            return;
        }
        out.w(indent, "w.write(\"", escape(text), "\");\n");
    }

    private static String escape(String in) {
        return in.replaceAll("\n", "\\\\n").replaceAll("\"", "\\\"");
    }

    @Override
    public boolean removeInitialNewline() {        
        if (text.startsWith("\r\n")) {
            text = text.substring(2);
            return true;
        } else if (text.startsWith("\n")) {
            text = text.substring(1);
            return true;
        }
        return false;
    }

    @Override
    public void appendNewline() {
        text += "\n";
    }

    @Override
    public void prepend(String preindent) {
        text = preindent + text;
    }

    @Override
    public String removeTrailingIndent() {
        int i = text.length() - 1;
        if (i == -1) {
            return "";
        }
        while(i > 0) {
            char c = text.charAt(i);
            if (c != ' ' && c != '\t') {
                i++;
                break;
            }
            i--;
        }
        String ret = text.substring(i); 
        text = text.substring(0, i);
        return ret;
    }

    public String text() {
        return text;
    }

    

}
