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

export const ImpactFactorsCard = () => {
  const factors = [
    { label: 'Orçamento Mensal Restante', impact: '-65%', status: 'negative' },
    { label: 'Reserva de Emergência', impact: 'Inalterada', status: 'neutral' },
    { label: 'Meta de Economia do Mês', impact: 'Atingida com risco', status: 'warning' },
  ];

  return (
    <View style={styles.card}>
      <Text style={styles.cardTitle}>Principais Motivos da Análise</Text>
      
      {factors.map((item, index) => {
       
        let statusColor = colors.success; 
       

        
        const isLastItem = index === factors.length - 1;

        return (
          <View 
            key={index} 
            style={[
              styles.factorRow, 
              { borderBottomWidth: isLastItem ? 0 : 1 }
            ]}
          >
            <Text style={styles.factorLabel}>{item.label}</Text>
            <Text style={[styles.factorImpact, { color: statusColor }]}>
              {item.impact}
            </Text>
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
    marginBottom: 16,
    
    // Mesma sombra e borda para manter a consistência com o card anterior
    borderWidth: 1,
    borderColor: colors.border,
    elevation: 1,
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 1 },
    shadowOpacity: 0.05,
    shadowRadius: 2,
  },
  cardTitle: {
    color: colors.text,
    fontSize: 16,
    fontWeight: 'bold',
    marginBottom: 12,
  },
  factorRow: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    paddingVertical: 12, // Aumentei um pouco o respiro para o tema claro
    borderBottomColor: colors.border,
  },
  factorLabel: { 
    color: colors.textSecondary, 
    fontSize: 14 
  },
  factorImpact: { 
    fontWeight: 'bold', 
    fontSize: 14 
  },
});