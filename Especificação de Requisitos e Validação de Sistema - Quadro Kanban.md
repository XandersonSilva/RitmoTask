# Descrição do Projeto – Quadro Kanban

Este projeto tem como objetivo o desenvolvimento de um sistema de gerenciamento de tarefas baseado no modelo **Kanban**. O sistema será acessado por diferentes tipos de usuários, como **administrador**, **líder**, **membro**, **convidado** e **visitante**, cada um com permissões específicas.

Entre os principais **requisitos funcionais**, estão funcionalidades como:

-   Cadastro, login e recuperação de senha de usuários;
    
-   Criação e gerenciamento de organizações, grupos e quadros Kanban;
    
-   Controle de tarefas com atribuições, prazos, comentários e status;
    
-   Compartilhamento de quadros com permissões personalizadas;
    
-   Integrações com serviços externos, como Google Calendar.
    

O sistema também contempla **requisitos não funcionais**, como:

-   Disponibilidade 24/7,
    
-   Interface responsiva para todos os dispositivos,
    
-   Segurança de dados,
    
-   Capacidade de escalar para grandes equipes.
    

Com base nesses requisitos, também foram definidos diversos **casos de uso** que representam as interações esperadas dos usuários com o sistema.

* **Atores do projeto**

    * Administrador
    * Líder
    * Membro
    * Convidado
    * Visitante

* **Requisitos funcionais**

| \[RF01\] | Cadastrar usuário |
| -- | -- |
| **Descrição** | O usuário poderá preencher um formulário com nome, e-mail, senha e outras informações, e ao enviar o formulário será criado um novo cadastro no sistema. |
| **Prioridade** | Essencial |
| **Caso de uso relacionado** | \[UC01\] |



| **\[RF02\]** | **Logar usuário** |
|--|--|
| **Descrição** | O usuário poderá acessar o sistema inserindo e-mail e senha, sendo redirecionado à tela inicial após autenticação válida. |
| **Prioridade** | Essencial |
| **Caso de uso relacionado** | \[UC02\] |



| **\[RF03\]** | **Recuperar senha** |
|--|--|
| **Descrição** | O sistema enviará um link de redefinição de senha ao e-mail do usuário mediante solicitação na tela de login. |
| **Prioridade** | Essencial |
| **Caso de uso relacionado** | \[UC03\] |



| **\[RF04\]** | **Deletar usuário** |
|--|--|
| **Descrição** | Permite que um usuário seja removido do sistema por um administrador ou pelo próprio dono da conta. |
| **Prioridade** | Alta |
| **Caso de uso relacionado** | \[UC04\] |



| **\[RF05\]** | **Apagar conta** |
|--|--|
| **Descrição** | O usuário poderá excluir permanentemente sua conta e todos os dados relacionados. |
| **Prioridade** | Essencial |
| **Caso de uso relacionado** | \[UC05\] |



| **\[RF06\]** | **Criar organização** |
|--|--|
| **Descrição** | Permite ao usuário criar uma nova organização com nome, descrição e membros associados. |
| **Prioridade** | Essencial |
| **Caso de uso relacionado** | \[UC06\] |



| **\[RF07\]** | **Deletar organização** |
|--|--|
| **Descrição** | Um administrador poderá remover uma organização e todos os dados associados, como quadros e grupos. |
| **Prioridade** | Alta |
| **Caso de uso relacionado** | \[UC07\] |



| **\[RF08\]** | **Criar grupo de trabalho** |
|--|--|
| **Descrição** | Usuários podem criar grupos dentro de organizações para dividir tarefas e quadros Kanban. |
| **Prioridade** | Essencial |
| **Caso de uso relacionado** | \[UC08\] |



| **\[RF09\]** | **Deletar grupo de trabalho** |
|--|--|
| **Descrição** | O sistema permitirá a exclusão de grupos de trabalho e seus dados por membros com permissão. |
| **Prioridade** | Alta |
| **Caso de uso relacionado** | \[UC09\] |



| **\[RF10\]** | **Criar Quadro Kanban** |
|--|--|
| **Descrição** | O usuário poderá criar quadros Kanban com colunas e personalizações para organizar tarefas. |
| **Prioridade** | Essencial |
| **Caso de uso relacionado** | \[UC10\] |



| **\[RF11\]** | **Editar Quadro Kanban** |
|--|--|
| **Descrição** | Os usuários com permissão poderão editar o nome, colunas e outras propriedades do quadro Kanban. |
| **Prioridade** | Essencial |
| **Caso de uso relacionado** | \[UC11\] |



| **\[RF12\]** | **Deletar Quadro Kanban** |
|--|--|
| **Descrição** | Um usuário com permissão poderá apagar quadros, excluindo todas as tarefas contidas nele. |
| **Prioridade** | Alta |
| **Caso de uso relacionado** | \[UC12\] |



| **\[RF13\]** | **Compartilhar Quadro Kanban** |
|--|--|
| **Descrição** | Usuários poderão compartilhar quadros com membros específicos da organização, concedendo permissões conforme necessário. |
| **Prioridade** | Essencial |
| **Caso de uso relacionado** | \[UC13\] |



| **\[RF14\]** | **Controle de permissões de usuário em quadros compartilhados** |

| **Descrição** | O criador do quadro poderá definir permissões de leitura, escrita ou administrador para cada membro que tiver acesso. |
| **Prioridade** | Essencial |
| **Caso de uso relacionado** | \[UC14\] |



| **\[RF15\]** | **Compartilhar quadro com link público com permissões limitadas** |

| **Descrição** | Permite gerar um link público para visualização ou edição, com níveis de acesso predefinidos pelo criador. |
| **Prioridade** | Alta |
| **Caso de uso relacionado** | \[UC15\] |



| **\[RF16\]** | **Modo somente leitura para convidados** |

| **Descrição** | Convidados poderão acessar quadros em modo somente leitura, sem a possibilidade de editar ou mover tarefas. |
| **Prioridade** | Alta |
| **Caso de uso relacionado** | \[UC16\] |



| **\[RF17\]** | **Criar tarefa** |
|--|--|
| **Descrição** | Os usuários poderão adicionar novas tarefas dentro de colunas específicas dos quadros Kanban. |
| **Prioridade** | Essencial |
| **Caso de uso relacionado** | \[UC17\] |



| **\[RF18\]** | **Editar tarefa** |
|--|--|
| **Descrição** | Permite modificar informações como título, descrição, data, responsável e status das tarefas. |
| **Prioridade** | Essencial |
| **Caso de uso relacionado** | \[UC18\] |



| **\[RF19\]** | **Deletar tarefa** |
|--|--|
| **Descrição** | O sistema permitirá excluir tarefas individualmente, removendo-as de seu respectivo quadro. |
| **Prioridade** | Alta |
| **Caso de uso relacionado** | \[UC19\] |



| **\[RF20\]** | **Cancelar tarefa** |
|--|--|
| **Descrição** | As tarefas poderão ser marcadas como canceladas, mantendo seu histórico para consulta futura. |
| **Prioridade** | Alta |
| **Caso de uso relacionado** | \[UC20\] |



| **\[RF21\]** | **Bloquear tarefa** |
|--|--|
| **Descrição** | Uma tarefa pode ser bloqueada para impedir sua movimentação ou edição até que o bloqueio seja removido. |
| **Prioridade** | Alta |
| **Caso de uso relacionado** | \[UC21\] |



| **\[RF22\]** | **Mover tarefa para o próximo estágio** |
|--|--|
| **Descrição** | O usuário poderá arrastar ou selecionar a opção de mover a tarefa entre colunas conforme o fluxo do quadro Kanban. |
| **Prioridade** | Essencial |
| **Caso de uso relacionado** | \[UC22\] |



| **\[RF23\]** | **Comentários e menções em tarefas** |
|--|--|
| **Descrição** | Os membros poderão inserir comentários e utilizar menções com “@” para notificar usuários específicos dentro das tarefas. |
| **Prioridade** | Essencial |
| **Caso de uso relacionado** | \[UC23\] |



| **\[RF24\]** | **Atribuir tarefa a usuário(s)** |
|--|--|
| **Descrição** | Cada tarefa poderá ser atribuída a um ou mais usuários para definir responsabilidades. |
| **Prioridade** | Essencial |
| **Caso de uso relacionado** | \[UC24\] |



| **\[RF25\]** | **Definir prazos e datas de entrega para tarefas** |
|--|--|
| **Descrição** | As tarefas poderão ter datas de início, entrega e lembretes configurados para melhor gestão de tempo. |
| **Prioridade** | Essencial |
| **Caso de uso relacionado** | \[UC25\] |



| **\[RF26\]** | **Sub tarefas (checklists) dentro de uma tarefa** |
|--|--|
| **Descrição** | Tarefas poderão conter sub tarefas organizadas como checklist, permitindo segmentação de atividades. |
| **Prioridade** | Essencial |
| **Caso de uso relacionado** | \[UC26\] |



| **\[RF27\]** | **Dashboard de desempenho** |
|--|--|
| **Descrição** | O sistema apresentará um painel com estatísticas sobre tarefas concluídas, em andamento, atrasadas e desempenho por usuário. |
| **Prioridade** | Alta |
| **Caso de uso relacionado** | \[UC27\] |



| **\[RF28\]** | **Modo offline com sincronização automática** |
|--|--|
| **Descrição** | O usuário poderá utilizar o sistema sem conexão com a internet, e os dados serão sincronizados automaticamente quando a conexão for restabelecida. |
| **Prioridade** | Baixa |
| **Caso de uso relacionado** | \[UC28\] |



| **\[RF29\]** | **Tags e filtros personalizados** |
|--|--|
| **Descrição** | As tarefas poderão ter etiquetas (tags) personalizadas, e será possível aplicar filtros por tag, status, responsáveis, entre outros. |
| **Prioridade** | Alta |
| **Caso de uso relacionado** | \[UC29\] |



| **\[RF30\]** | **Notificações configuráveis** |
|--|--|
| **Descrição** | O sistema permitirá configurar os tipos de notificações que o usuário deseja receber e os canais (e-mail, push, etc.). |
| **Prioridade** | Alta |
| **Caso de uso relacionado** | \[UC30\] |



| **\[RF31\]** | **Integração com Google Calendar** |
|--|--|
| **Descrição** | As tarefas com prazos poderão ser sincronizadas com o Google Calendar, criando eventos automaticamente. |
| **Prioridade** | Alta |
| **Caso de uso relacionado** | \[UC31\] |

* **Requisitos não funcionais**

| \[RNF01\] | Disponibilidade de 24/7 |
| -- | -- |
| **Descrição** | O sistema deve estar disponível para acesso e uso contínuo 24 horas por dia, 7 dias por semana, com exceção de eventuais janelas de manutenção programadas. |
| **Prioridade** | Essencial |
| **Caso de uso relacionado** | Aplicável a todos os casos de uso |



| **\[RNF02\]** | **Interface amigável e responsiva para todos os dispositivos** |
|--|--|
| **Descrição** | A interface deve ser intuitiva, de fácil navegação e adaptável a diferentes tamanhos de tela, como desktops, tablets e smartphones. |
| **Prioridade** | Alta |
| **Caso de uso relacionado** | Todos os que envolvem interação do usuário com o sistema (\[UC01\] a \[UC31\]) |



| **\[RNF03\]** | **Garantir segurança dos dados** |
|--|--|
| **Descrição** | O sistema deve adotar práticas de segurança como criptografia, autenticação segura, e proteção contra ataques. |
| **Prioridade** | Essencial |
| **Caso de uso relacionado** | \[UC01\], \[UC02\], \[UC03\], \[UC04\], \[UC05\], \[UC13\], \[UC14\], \[UC15\] |



| **\[RNF04\]** | **Escalabilidade para suportar grandes equipes e grandes quadros** |
|--|--|
| **Descrição** | A aplicação deve ser projetada para crescer horizontalmente e manter desempenho adequado mesmo com grandes volumes de dados e usuários simultâneos. |
| **Prioridade** | Alta |
| **Caso de uso relacionado** | \[UC06\], \[UC07\], \[UC10\], \[UC27\] |

* **Descrição dos casos de** 

| \[UC01\] | Cadastrar usuário |
| -- | -- |
| Descrição | Cria um perfil para acesso ao sistema, de forma que por padrão cada usuário é administrador de sua própria conta e possui controle total de todas as estruturas criadas por ele enquanto atua como administrador. |
| Atores | Todos |
| Prioridade |  |
| Pré-condições |  |
| Fluxo de eventos |  |


| **\[UC02\]** | **Logar usuário** |
|--|--|
| Descrição |  |
| Atores | Todos |
| Prioridade |  |
| Pré-condições |  |
| Fluxo de eventos |  |


| **\[UC03\]** | **Recuperar senha** |
|--|--|
| Descrição |  |
| Atores | Todos |
| Prioridade |  |
| Pré-condições |  |
| Fluxo de eventos |  |


| **\[UC04\]** | **Deletar usuário** |
|--|--|
| Descrição |  |
| Atores | Administrador |
| Prioridade |  |
| Pré-condições |  |
| Fluxo de eventos |  |


| **\[UC05\]** | **Apagar conta** |
|--|--|
| Descrição |  |
| Atores | Todos |
| Prioridade |  |
| Pré-condições |  |
| Fluxo de eventos |  |


| **\[UC06\]** | **Criar organização** |
|--|--|
| Descrição |  |
| Atores | Administrador |
| Prioridade |  |
| Pré-condições |  |
| Fluxo de eventos |  |


| **\[UC07\]** | **Deletar organização** |
|--|--|
| Descrição |  |
| Atores | Administrador |
| Prioridade |  |
| Pré-condições |  |
| Fluxo de eventos |  |


| **\[UC08\]** | **Criar grupo de trabalho** |
|--|--|
| Descrição |  |
| Atores | Administrador, Líder |
| Prioridade |  |
| Pré-condições |  |
| Fluxo de eventos |  |


| **\[UC09\]** | **Deletar grupo de trabalho** |
|--|--|
| Descrição |  |
| Atores | Administrador, Líder |
| Prioridade |  |
| Pré-condições |  |
| Fluxo de eventos |  |


| **\[UC10\]** | **Criar Quadro Kanban** |
|--|--|
| Descrição |  |
| Atores | Administrador, Líder |
| Prioridade |  |
| Pré-condições |  |
| Fluxo de eventos |  |


| **\[UC11\]** | **Editar Quadro Kanban** |
|--|--|
| Descrição |  |
| Atores | Administrador, Líder |
| Prioridade |  |
| Pré-condições |  |
| Fluxo de eventos |  |


| **\[UC12\]** | **Deletar Quadro Kanban** |
|--|--|
| Descrição |  |
| Atores | Administrador, Líder |
| Prioridade |  |
| Pré-condições |  |
| Fluxo de eventos |  |


| **\[UC13\]** | **Compartilhar Quadro Kanban** |
|--|--|
| Descrição | Um administrador ou líder pode compartilhar um quadro para que outros usuários possam acessá-lo pela plataforma. Assim sendo, o quadro deve ser listado, para o usuário que recebeu o privilégio, juntamente com os que já eram listados anteriormente ao compartilhamento. |
| Atores | Administrador, Líder |
| Prioridade |  |
| Pré-condições |  |
| Fluxo de eventos |  |


| **\[UC14\]** | **Controle de permissões em quadros compartilhados** |
|--|--|
| Descrição | Um administrador ou líder pode mudar o papel de cada usuário que tem acesso ao sistema (Líder, Membro, Visitante) ou removê-lo totalmente. |
| Atores | Administrador, Líder |
| Prioridade |  |
| Pré-condições |  |
| Fluxo de eventos |  |


| **\[UC15\]** | **Compartilhar quadro com link público** |
|--|--|
| Descrição | Cria um link para compartilhar o  |
| Atores | Administrador, Líder |
| Prioridade | Normal |
| Pré-condições |  |
| Fluxo de eventos |  |


| **\[UC16\]** | **Acessar quadro como convidado (somente leitura)** |
|--|--|
| Descrição |  |
| Atores | Todos |
| Prioridade |  |
| Pré-condições |  |
| Fluxo de eventos |  |


| **\[UC17\]** | **Criar tarefa** |
|--|--|
| Descrição |  |
| Atores | Líder, Membro |
| Prioridade |  |
| Pré-condições |  |
| Fluxo de eventos |  |


| **\[UC18\]** | **Editar tarefa** |
|--|--|
| Descrição |  |
| Atores | Líder, Membro |
| Prioridade |  |
| Pré-condições |  |
| Fluxo de eventos |  |


| **\[UC19\]** | **Deletar tarefa** |
|--|--|
| Descrição |  |
| Atores | Líder |
| Prioridade |  |
| Pré-condições |  |
| Fluxo de eventos |  |


| **\[UC20\]** | **Cancelar tarefa** |
|--|--|
| Descrição |  |
| Atores | Líder |
| Prioridade |  |
| Pré-condições |  |
| Fluxo de eventos |  |


| **\[UC21\]** | **Bloquear tarefa** |
|--|--|
| Descrição |  |
| Atores | Líder |
| Prioridade |  |
| Pré-condições |  |
| Fluxo de eventos |  |


| **\[UC22\]** | **Mover tarefa para o próximo estágio** |
|--|--|
| Descrição |  |
| Atores | Líder, Membro |
| Prioridade |  |
| Pré-condições |  |
| Fluxo de eventos |  |


| **\[UC23\]** | **Inserir comentários e menções** |
|--|--|
| Descrição |  |
| Atores | Líder, Membro |
| Prioridade |  |
| Pré-condições |  |
| Fluxo de eventos |  |


| **\[UC24\]** | **Atribuir tarefa a usuários** |
|--|--|
| Descrição |  |
| Atores | Líder |
| Prioridade |  |
| Pré-condições |  |
| Fluxo de eventos |  |


| **\[UC25\]** | **Definir prazos e datas de entrega** |
|--|--|
| Descrição |  |
| Atores | Líder |
| Prioridade |  |
| Pré-condições |  |
| Fluxo de eventos |  |


| **\[UC26\]** | **Criar subtarefas/checklists** |
|--|--|
| Descrição |  |
| Atores | Líder, Membro |
| Prioridade |  |
| Pré-condições |  |
| Fluxo de eventos |  |


| **\[UC27\]** | **Visualizar Dashboard de desempenho** |
|--|--|
| Descrição |  |
| Atores | Administrador, Líder, Membro |
| Prioridade |  |
| Pré-condições |  |
| Fluxo de eventos |  |


| **\[UC28\]** | **Usar sistema no modo offline** |
|--|--|
| Descrição |  |
| Atores | Administrador, Líder, Membro |
| Prioridade |  |
| Pré-condições |  |
| Fluxo de eventos |  |


| **\[UC29\]** | **Aplicar tags e filtros personalizados** |
|--|--|
| Descrição |  |
| Atores | Administrador, Líder, Membro |
| Prioridade |  |
| Pré-condições |  |
| Fluxo de eventos |  |


| **\[UC30\]** | **Configurar notificações** |
|--|--|
| Descrição |  |
| Atores | Administrador, Líder, Membro |
| Prioridade |  |
| Pré-condições |  |
| Fluxo de eventos |  |


| **\[UC31\]** | **Integrar com Google Calendar** |
|--|--|
| Descrição |  |
| Atores | Administrador, Líder, Membro |
| Prioridade |  |
| Pré-condições |  |
| Fluxo de eventos |  |

