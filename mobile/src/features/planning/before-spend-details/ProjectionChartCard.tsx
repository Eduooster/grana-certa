import React from 'react';
import { View, Text, StyleSheet } from 'react-native';

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

export const ProjectionChartCard = () => {
  return (
    <View style={styles.card}>
      <Text style={styles.title}>Projeção de Saldo Até o Fim do Mês</Text>
      <Text style={styles.subtitle}>Comparativo: Com o gasto vs. Sem o gasto</Text>

      {/* Placeholder do Gráfico */}
      <View style={styles.chartPlaceholder}>
        <Text style={styles.placeholderText}>[ Espaço Reservado para o Gráfico ]</Text>
        <Text style={styles.placeholderSubtext}>
          Visualização da linha de saldo ao longo dos dias
        </Text>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  card: {
    backgroundColor: colors.surface,
    borderRadius: 12,
    padding: 16,
    marginBottom: 16,

    // Padrão de borda e sombra para o tema claro
    borderWidth: 1,
    borderColor: colors.border,
    elevation: 1,
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 1 },
    shadowOpacity: 0.05,
    shadowRadius: 2,
  },
  title: {
    color: colors.text,
    fontSize: 16,
    fontWeight: 'bold',
  },
  subtitle: {
    color: colors.textSecondary,
    fontSize: 12,
    marginBottom: 16,
  },
  chartPlaceholder: {
    height: 180,
    backgroundColor: colors.background,
    borderRadius: 8,
    borderWidth: 1,
    borderColor: colors.border,
    borderStyle: 'dashed',
    justifyContent: 'center',
    alignItems: 'center',
    padding: 16,
  },
  placeholderText: {
    color: colors.primary,
    fontWeight: 'bold',
    fontSize: 14,
  },
  placeholderSubtext: {
    color: colors.textSecondary,
    fontSize: 12,
    marginTop: 4,
    textAlign: 'center',
  },
});