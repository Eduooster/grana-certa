export type SimulationStatus = "safe" | "attention" | "danger";

export type SimulationResult = {
  status: SimulationStatus;
  availableAfter: string;
  marginImpact: string;
  analysis: string;
};

export type UpcomingTransaction = {
  id: string;
  title: string;
  date: string;
  amount: string;
  icon: string;
  iconColor: string;
};

export const mockSimulation = (amount: number): SimulationResult => {
  if (amount <= 500) {
    return {
      status: "safe",
      availableAfter: "R$ 5.650",
      marginImpact: "40%",
      analysis:
        "Esse gasto cabe no seu orçamento e mantém sua margem segura. Você ainda terá espaço para lidar com despesas inesperadas.",
    };
  }

  if (amount <= 1000) {
    return {
      status: "attention",
      availableAfter: "R$ 5.150",
      marginImpact: "80%",
      analysis:
        "Esse gasto é possível, mas reduz bastante sua margem segura. Antes de realizar a compra, considere se ela é realmente necessária.",
    };
  }

  return {
    status: "danger",
    availableAfter: "R$ 4.650",
    marginImpact: "120%",
    analysis:
      "Esse gasto compromete sua margem segura e reduz significativamente sua capacidade de lidar com imprevistos. O momento exige cautela.",
  };
};

export const upcomingTransactions: UpcomingTransaction[] = [
  {
    id: "1",
    title: "Aluguel",
    date: "10 de setembro",
    amount: "R$ 1.200",
    icon: "home-outline",
    iconColor: "#6366F1",
  },
  {
    id: "2",
    title: "Fatura do cartão",
    date: "15 de setembro",
    amount: "R$ 850",
    icon: "card-outline",
    iconColor: "#8B5CF6",
  },
  {
    id: "3",
    title: "Internet",
    date: "18 de setembro",
    amount: "R$ 120",
    icon: "wifi-outline",
    iconColor: "#0EA5E9",
  },
  {
    id: "4",
    title: "Netflix",
    date: "20 de setembro",
    amount: "R$ 55",
    icon: "tv-outline",
    iconColor: "#EF4444",
  },
];