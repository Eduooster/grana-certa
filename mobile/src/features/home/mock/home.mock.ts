

import { BalanceCardType } from "../components/BalanceCard";
import { AvailableBalance, FinancialInsight, FinancialSummary, HomeHeaderType, MonthlyFlow, RecentTransaction, UpcomingCommitment } from "../types/home";



export const homeHeaderMock: HomeHeaderType = {
  userName: "Eduardo",
  lastSyncAt: "2026-09-04T14:30:00",
  isSynced: true,
};

export const availableBalanceMock: AvailableBalance = {
  amount: 6150,
  safeMargin: 1850,
};

export const balanceMock: BalanceCardType = {
  balance: 8450,
  committed: 2300,
  connectedAccounts: 3,
};
export const financialSummaryMock: FinancialSummary = {
  balance: 8200,
  receivables: 2500,
  upcomingCommitments: 2050,
  creditCardDue: 850,
};

export const upcomingCommitmentsMock: UpcomingCommitment[] = [
  {
    id: "1",
    title: "Aluguel",
    amount: 1200,
    dueDate: "2026-09-10",
    category: "Moradia",
  },
  {
    id: "2",
    title: "Cartão de crédito",
    amount: 850,
    dueDate: "2026-09-12",
    category: "Cartão",
  },
  {
    id: "3",
    title: "Internet",
    amount: 120,
    dueDate: "2026-09-15",
    category: "Serviços",
  },
];

export const monthlyFlowMock: MonthlyFlow[] = [
  {
    month: "Abr",
    income: 5200,
    expenses: 3800,
  },
  {
    month: "Mai",
    income: 4800,
    expenses: 3500,
  },
  {
    month: "Jun",
    income: 5100,
    expenses: 4100,
  },
  {
    month: "Jul",
    income: 5400,
    expenses: 3900,
  },
  {
    month: "Ago",
    income: 5000,
    expenses: 3600,
  },
  {
    month: "Set",
    income: 4500,
    expenses: 2100,
  },
];

export const recentTransactionsMock: RecentTransaction[] = [
  {
    id: "1",
    description: "Supermercado",
    amount: 185.9,
    date: "2026-09-04T10:30:00",
    type: "expense",
    category: "Alimentação",
  },
  {
    id: "2",
    description: "Salário",
    amount: 4500,
    date: "2026-09-01T08:00:00",
    type: "income",
    category: "Salário",
  },
  {
    id: "3",
    description: "Uber",
    amount: 32.5,
    date: "2026-08-31T19:20:00",
    type: "expense",
    category: "Transporte",
  },
];

export const financialInsightMock: FinancialInsight = {
  title: "Sua margem está saudável",
  description:
    "Seus compromissos previstos estão cobertos e você ainda mantém uma margem para gastos inesperados.",
  type: "positive",
};