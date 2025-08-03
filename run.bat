@echo off
echo "=== EXECUÇÃO DO PROJETO AV3PDP ==="

if not exist "bin\br\ifba\edu\aval\AppAvaliacao3.class" (
    echo "Projeto nao compilado. Execute build.bat primeiro!"
    pause
    exit /b 1
)

echo "Executando AppAvaliacao3..."
echo "================================"
java -cp bin br.ifba.edu.aval.AppAvaliacao3

echo "================================"
echo "Execucao concluida!"
pause