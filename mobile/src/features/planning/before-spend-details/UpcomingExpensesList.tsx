import React from 'react';
import { View, Text, TouchableOpacity, StyleSheet } from 'react-native';

// Importe suas variáveis de cor
export const colors = {
  primary: "#6366F1",
  secondary: "#8B5CF6",
  background: "#F8FAFC",
  surface: "#FFFFFF",
  text: "#0F172A",
  textSecondary: "#64748B",
  border: "#E2E8F0",
  success: "#22C55E",
  warning: "#F59E0B",
  danger: "#EF4444",
} as const;

export const UpcomingExpensesList = () => {
  const expenses = [
    { id: '1', name: 'Aluguel / Condomínio', date: '10/Out', value: 'R$ 1.400,00' },
    { id: '2', name: 'Fatura do Cartão', date: '15/Out', value: 'R$ 850,00' },
    { id: '3', name: 'Conta de Luz & Internet', date: '18/Out', value: 'R$ 230,00' },
  ];

  const handleSeeAll = () => {
    console.log('Navegar para todos os gastos previstos');
  };

  return (
    <View style={styles.card}>
      <View style={styles.header}>
        <View>
          <Text style={styles.title}>Gastos Já Previstos</Text>
          <Text style={styles.subtitle}>Compromissos agendados até o fim do mês</Text>
        </View>
        <TouchableOpacity onPress={handleSeeAll}>
          <Text style={styles.seeAllText}>Ver todos</Text>
        </TouchableOpacity>
      </View>

      {expenses.map((item, index) => {
        const isLastItem = index === expenses.length - 1;

        return (
          <View
            key={item.id}
            style={[
              styles.itemRow,
              { borderBottomWidth: isLastItem ? 0 : 1 }
            ]}
          >
            <View>
              <Text style={styles.itemName}>{item.name}</Text>
              <Text style={styles.itemDate}>Vence {item.date}</Text>
            </View>
            <Text style={styles.itemValue}>{item.value}</Text>
          </View>
        );
      })}
    </View>
  );
};

const styles = StyleSheet.create({
  card: {
    backgroundColor: colors.surface,
    borderRadius: 12,
    padding: 16,
    marginBottom: 24,

    // Padrão de borda e sombra leve
    borderWidth: 1,
    borderColor: colors.border,
    elevation: 1,
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 1 },
    shadowOpacity: 0.05,
    shadowRadius: 2,
  },
  header: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'flex-start',
    marginBottom: 16,
  },
  title: {
    color: colors.text,
    fontSize: 16,
    fontWeight: 'bold',
  },
  subtitle: {
    color: colors.textSecondary,
    fontSize: 12,
  },
  seeAllText: {
    color: colors.primary,
    fontSize: 14,
    fontWeight: '600',
  },
  itemRow: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    paddingVertical: 12,
    borderBottomColor: colors.border,
  },
  itemName: {
    color: colors.text,
    fontSize: 14,
    fontWeight: '500',
  },
  itemDate: {
    color: colors.textSecondary,
    fontSize: 12,
    marginTop: 2,
  },
  itemValue: {
    color: colors.text,
    fontSize: 14,
    fontWeight: 'bold',
  },
});