package com.example.filter.text.demo;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.ahocorasick.trie.Emit;
import org.ahocorasick.trie.Trie;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

        Set<String> stringSet = new HashSet<>();

        stringSet.add("习近平");
        stringSet.add("法轮功");
        stringSet.add("共产党");
        stringSet.add("威信：uaa362");

        String[] value = new String[stringSet.size()];
        stringSet.toArray(value);

        Trie.TrieBuilder trieBuilder = Trie.builder();

        if (false) {
            trieBuilder.stopOnHit();
        }

        for (String v : value) {
            trieBuilder.addKeyword(v);
        }

        Trie trie = trieBuilder.build();

        String text = "【全联】亲爱的全联云商户，非常抱歉，您实名制认证失败；请您重新核实信息；短链：http://quanlianyun.aalib.com.cn ；如需退订，回复TD";
        System.out.println("查看是否包含这些字符");
        System.out.println(trie.containsMatch(text));
        System.out.println("查看包含了哪些字符");

        Collection<Emit> emits = trie.parseText(text);
        for (Emit emit : emits) {
            System.out.println(emit.getKeyword());
        }

    }

}
