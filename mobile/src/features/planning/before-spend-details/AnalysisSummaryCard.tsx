import React from 'react';
import { View, Text, StyleSheet } from 'react-native';

// Supondo que você importe as cores do seu arquivo de tema, ex:
// import { colors } from '../../constants/colors';
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

interface AnalysisSummaryProps {
  status?: 'warning' | 'danger' | 'success';
  title?: string;
  description?: string;
}

export const AnalysisSummaryCard = ({
  status = 'danger',
  title = 'Gasto Não Recomendado',
  description = 'Esta compra compromete mais de 70% do seu saldo livre restante para o mês e reduz sua margem de segurança.',
}: AnalysisSummaryProps) => {
  
  // Mapeia a cor baseada no status passado (danger, warning ou success)
  const statusColor = colors[status];

  return (
    <View style={[styles.card, { borderLeftColor: statusColor }]}>
      <Text style={[styles.badge, { color: statusColor }]}>
        Análise de Impacto
      </Text>
      <Text style={styles.title}>{title}</Text>
      <Text style={styles.description}>{description}</Text>
    </View>
  );
};

const styles = StyleSheet.create({
  card: {
    backgroundColor: colors.surface,
    borderRadius: 12,
    padding: 16,
    marginBottom: 16,
    borderLeftWidth: 4,
    
    // Adicionando borda e sombra leves para destacar o card no fundo claro
    borderWidth: 1,
    borderColor: colors.border,
    elevation: 1, // Sombra sutil no Android
    shadowColor: '#000', // Sombra sutil no iOS
    shadowOffset: { width: 0, height: 1 },
    shadowOpacity: 0.05,
    shadowRadius: 2,
  },
  badge: {
    fontSize: 12,
    fontWeight: 'bold',
    textTransform: 'uppercase',
    marginBottom: 6,
  },
  title: {
    color: colors.text,
    fontSize: 18,
    fontWeight: 'bold',
    marginBottom: 6,
  },
  description: {
    color: colors.textSecondary,
    fontSize: 14,
    lineHeight: 20,
  },
});