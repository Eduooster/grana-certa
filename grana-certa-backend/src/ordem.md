| Ordem | Entidade              | Por quê                                              |
| ----- | --------------------- | ---------------------------------------------------- |
| 1     | `User`                | É a raiz de identidade e propriedade                 |
| 2     | `FinancialProfile`    | Configurações/preferências ligadas ao usuário        |
| 3     | `FinancialAccount`    | Base para representar onde o dinheiro está           |
| 4     | `Category`            | Necessária para classificar movimentações            |
| 5     | `Transaction`         | É o núcleo operacional do sistema                    |
| 6     | `Transfer`            | Depende das transações/contas                        |
| 7     | `Recurrence`          | Gera/define transações futuras                       |
| 8     | `Invoice`             | Depende fortemente da modelagem de cartão/transações |
| 9     | `Goal`                | Usa informações financeiras já existentes            |
| 10    | `FinancialConnection` | Integração externa; não deve ditar o domínio         |
| 11    | `Notification`        | É consequência de outros fluxos                      |
