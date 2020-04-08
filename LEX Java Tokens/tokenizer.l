%{
FILE* yyin;
%}

DIGIT [0-9]
NUMBER {DIGIT}+
REAL {DIGIT}*[.]{NUMBER}
TEXT [A-Za-z]
KEYWORDS "class"|"static"|"native"
DATATYPE "int"|"float"|"double"|"boolean"|"String"|"void"
CONDITIONAL "if"|"else"|"else if"|"switch"|"case"
ITERATIVE "for"|"while"|"do"
DEL ";"
PREPROCESSOR "import"[^\n]*";"
IDENTIFIER {TEXT}({DIGIT}|{TEXT}|"_")*
NONIDENTIFIER {DIGIT}({TEXT}|{DIGIT}|"_")*
ARITHMATIC_OP "+"|"-"|"*"|"/"|"%"
LOGICAL_OP "&&"|"||"|"!"|"!="
RELATIONAL_OP "<"|">"|"<="|">="|"=="
UNARY_OP "++"|"--"
ACCESS_SP "public"|"private"|"protected"|""
FUNCTION {ACCESS_SP}{DATATYPE}{IDENTIFIER}"("({DATATYPE}{IDENTIFIER})*")"

%%
[ \n\t]+ ;
{PREPROCESSOR} {printf("%s\t --> Preprocessor\n", yytext);}
{CONDITIONAL} {printf("%s\t --> Conditional\n", yytext);}
{ITERATIVE} {printf("%s\t --> Iterative Construct\n", yytext);}
{DATATYPE} {printf("%s\t --> Datatype\n", yytext);}
{ACCESS_SP} {printf("%s\t --> Access Specifier\n", yytext);}
{KEYWORDS} {printf("%s\t --> Keywords\n", yytext);}
{IDENTIFIER} {printf("%s\t --> Identifiers\n", yytext);}
{REAL} {printf("%s\t --> Real Constants\n", yytext);}
{NUMBER} {printf("%s\t --> Constant Integer\n", yytext);}
{NONIDENTIFIER} {printf("%s\t --> Non Identifier\n", yytext);}
{DEL} {printf("%s\t --> Delimiter\n", yytext);}
{UNARY_OP} {printf("%s\t --> Unary Operator\n", yytext);}
{ARITHMATIC_OP} {printf("%s\t --> Arithmetic Operator\n", yytext);}
{LOGICAL_OP} {printf("%s\t --> Logical Operator\n", yytext);}
{RELATIONAL_OP} {printf("%s\t --> Relational Operator\n", yytext);}
"=" {printf("%s\t --> Assignment Operator\n", yytext);}
"{" {printf("%s\t --> Code Block Begin\n", yytext);}
"}" {printf("%s\t --> Code Block End\n", yytext);}
"(" {printf("%s\t --> Paranthesis Begin\n", yytext);}
")" {printf("%s\t --> Paranthesis End\n", yytext);}
. ;
%%

int yywrap(){
	return 1;
}

int main(int argc, char* argv[]){
	yyin = fopen(argv[1], "r");
	yylex();
	fclose(yyin);
}