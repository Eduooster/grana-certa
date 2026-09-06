// src/features/home/types/home.types.ts

export type HomeHeaderType = {
  userName: string;
  lastSyncAt: string;
  isSynced: boolean;
};

export type AvailableBalance = {
  amount: number;
  safeMargin: number;
};

export type FinancialSummary = {
  balance: number;
  receivables: number;
  upcomingCommitments: number;
  creditCardDue: number;
};

export type UpcomingCommitment = {
  id: string;
  title: string;
  amount: number;
  dueDate: string;
  category: string;
};

export type MonthlyFlow = {
  month: string;
  income: number;
  expenses: number;
};

export type RecentTransaction = {
  id: string;
  description: string;
  amount: number;
  date: string;
  type: "income" | "expense";
  category: string;
};

export type FinancialInsight = {
  title: string;
  description: string;
  type: "positive" | "warning" | "info";
};