## Achievements:

Explored and implemented the lambda and stream features of Java 8 to efficiently process file and string processing.


## **RegexTextReplacementInFiles**

The program takes 3 required parameters and one optional parameter: o Starting directory/file

o String pattern to be replaced - in Java supported regular expression, see (1) o String to be replaced with o File naming pattern - UNIX wild-card filename syntax, see (2)

### Notes

1. The string pattern is a JDK supported regular expression with exactly 1 match group.  Find all texts, which match the pattern, and replace **ONLY** the match group 1 with the replacement. If the match group in the given pattern doesn&#39;t equal to one, the program can exit immediately by throwing and/or printing error.  
2. The file naming pattern will be the generally accepted file naming pattern in UNIX where &#39;\*&#39; matches any number of characters, &#39;?&#39; matches one character.  


## Sample Input / Output (sample in UNIX OS, please adjust for your OS when necessary)

./scripts/regexTextReplacementInFiles sample\_dir &#39;\w\*(lan)\w+&#39; &#39;&lt;-replaced-&gt;&#39; \*.txt Processedg 3 files

Replaced to &#39;&lt;-replaced-&gt;&#39;: \* Planitia : 1 occurence \* lander : 1 occurence \* landing : 5 occurences

\* lands : 1 occurence \* plans : 1 occurence \* plants : 1 occurrence

$ ls -R sample\_dir

abcde.txt

dummy.doc

sample\_dir/folder1: folder1-sample1.txt folder1-sample2.txt

abcde.txt.processed folder1

folder1-sample1.txt.processed folder1-sample2.txt.processed

$ cat sample\_dir/folder1/folder1-sample2.txt.processed ... Watney p&lt;--‐replaced--‐&gt;s to drive 3,200 kilometres (2,000 mi) to Schiaparelli crater when the Ares 4 mission &lt;--‐replaced--‐&gt;ds there in four years. He begins modifying one of Ares 3&#39;s rovers for the journey, adding solar cells and an additional battery. He makes a long test drive to recover the unmanned Pathfinder &lt;--‐replaced--‐&gt;der and Sojourner rover and brings them back ...


