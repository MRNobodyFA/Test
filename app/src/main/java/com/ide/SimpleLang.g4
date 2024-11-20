grammar SimpleLang;

prog:   stat+ ;
stat:   ID '=' expr NEWLINE
    |   expr NEWLINE
    ;
expr:   expr ('*'|'/') expr
    |   expr ('+'|'-') expr
    |   INT
    |   ID
    |   '(' expr ')'
    ;
ID  :   [a-zA-Z]+ ;
INT :   [0-9]+ ;
NEWLINE: '\r'? '\n' ;
WS  :   [ \t]+ -> skip ;
