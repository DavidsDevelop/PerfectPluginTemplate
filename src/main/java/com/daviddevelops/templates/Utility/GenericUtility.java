package com.daviddevelops.templates.Utility;

import com.daviddevelops.templates.Inventories.API.*;
import com.daviddevelops.templates.Inventories.API.content.*;
import com.daviddevelops.templates.Utility.ColorsAPI.*;
import net.md_5.bungee.api.*;
import net.md_5.bungee.api.chat.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;

import java.util.*;

public class GenericUtility {

    //    --[ Math related Functions  ]--
    /**
     * @desc Evaluates and computes math from String. Used for computing math from Config files.
     */
    public double evaluateMath(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)` | number
            //        | functionName `(` expression `)` | functionName factor
            //        | factor `^` factor

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return +parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    if (!eat(')')) throw new RuntimeException("Missing ')'");
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    if (eat('(')) {
                        x = parseExpression();
                        if (!eat(')')) throw new RuntimeException("Missing ')' after argument to " + func);
                    } else {
                        x = parseFactor();
                    }
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }

    public ClickableItem registerItem(ItemStack itemStack, String s) {
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(translateColors(s));
        itemStack.setItemMeta(meta);
        return ClickableItem.empty(itemStack);
    }

    public ClickableItem registerItem(ItemStack itemStack, String s, List<String> l) {
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(translateColors(s));
        meta.setLore(translateColors(l));
        itemStack.setItemMeta(meta);
        return ClickableItem.empty(itemStack);
    }

    public ItemStack recolorItem(ItemStack itemStack, String s, List<String> l) {
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(translateColors(s));
        meta.setLore(translateColors(l));
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack recolorItem(ItemStack itemStack, String s) {
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(translateColors(s));
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    //    --[ Chat related Functions  ]--
    /**
     * @desc Translates Strings color. (Hex & Code supported)
     */
    public String translateColors(String s){
        return ColorAPI.process(s);
    }

    public void sendPlayerMessage(Player player, String message, boolean actionBar){
        if(actionBar){
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(translateColors(message)));
            return;
        }
        player.sendMessage(translateColors(message));
    }

    /**
     * @desc Translates String[] color. (Hex & Code supported)
     */
    public List<String> translateColors(List<String> s){
        List<String> newList = new ArrayList<>();
        if(s ==null){
            return newList;
        }
        for(String msg : s){
            newList.add(translateColors(msg));
        }
        return newList;
    }

}
