@echo off
echo "=== BUILD DO PROJETO AV3PDP ==="
echo "Compilando arquivos Java..."

javac -d bin -cp src src/br/ifba/edu/aval/*.java src/br/ifba/edu/aval/model/*.java src/br/ifba/edu/aval/exception/*.java src/br/ifba/edu/aval1/builder/*.java src/br/ifba/edu/aval1/prototype/*.java src/br/ifba/edu/aval2/bridge/*.java src/br/ifba/edu/aval2/decorator/*.java

if %ERRORLEVEL% == 0 (
    echo "Compilacao concluida com sucesso!"
    echo "Para executar use: java -cp bin br.ifba.edu.aval.AppAvaliacao3"
) else (
    echo "Erro na compilacao!"
)

pause