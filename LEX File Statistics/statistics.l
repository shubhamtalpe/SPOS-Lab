%{
	int no_lines, no_words, no_chars;
%}

%%
\n {no_chars++; no_lines++;}
[^ \n\t]+ {no_words++; no_chars+=yyleng;}
. {no_chars++;}

%%

int yywrap(){
	return 1;
}

int main(int argc, char* argv[]){
	yyin = fopen(argv[1], "r");
	yylex();
	printf("No. of lines: %d\nNo. of words: %d\nNo. of characters: %d\n",no_lines+1, no_words, no_chars);
}