/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mailsender;

/**
 *
 * @author jcarrillo
 */
public class Password {
    final String password; // the string to mask
    int i;
    Password(String password) { this.password = password; } // needs null protection
    // allow this to be equal to any string
    // reconsider this approach if adding it to a map or something?
    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        return password.equals(o);
    }
    // we don't need anything special that the string doesnt
    @Override
    public int hashCode() { return password.hashCode(); }
    // send stars if anyone asks to see the string - consider sending just
    // "******" instead of the length, that way you don't reveal the password's length
    // which might be protected information
    /**
     *
     * @return
     */
    @SuppressWarnings("empty-statement")
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < password.length(); i++) 
            sb.append("*");
        return sb.toString();
    }
    
}
